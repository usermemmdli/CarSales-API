package com.example.Car_SalesAPI.service;

import com.example.Car_SalesAPI.dao.entity.Announcement;
import com.example.Car_SalesAPI.dao.entity.Cards;
import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dao.repository.AnnouncementRepository;
import com.example.Car_SalesAPI.dao.repository.CardsRepository;
import com.example.Car_SalesAPI.dao.repository.UsersRepository;
import com.example.Car_SalesAPI.dto.request.CardRequest;
import com.example.Car_SalesAPI.dto.request.UserChangePasswordRequest;
import com.example.Car_SalesAPI.dto.request.UserDeleteRequest;
import com.example.Car_SalesAPI.dto.request.UserEditRequest;
import com.example.Car_SalesAPI.dto.response.AccountResponse;
import com.example.Car_SalesAPI.dto.response.CardsResponse;
import com.example.Car_SalesAPI.dto.response.UserProfileResponse;
import com.example.Car_SalesAPI.exception.CardsNotFoundException;
import com.example.Car_SalesAPI.exception.InvalidEmailOrPasswordException;
import com.example.Car_SalesAPI.mapper.CardsMapper;
import com.example.Car_SalesAPI.mapper.UsersMapper;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final AuthenticationHelperService authenticationHelperService;
    private final PasswordEncoder passwordEncoder;
    private final CardsRepository cardsRepository;
    private final CardsMapper cardsMapper;
    private final AnnouncementRepository announcementRepository;

    public AccountResponse getAccountInfo(String currentPhoneNumber) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);

        List<Announcement> allAnnouncements = announcementRepository.findAllById(user.getAnnouncementId());
        Integer countAnnouncements = allAnnouncements.size();
        Integer countRejectedAnnouncements = allAnnouncements
                .stream()
                .filter(announcement -> announcement.getIsActive() == false)
                .toList().size();
        Integer countExpiredAnnouncements = allAnnouncements
                .stream()
                .filter(announcement -> announcement.getExpireDate().before(Date.from(Instant.now())))
                .toList().size();

        return AccountResponse.builder()
                .countAnnouncements(countAnnouncements)
                .countRejectedAnnouncements(countRejectedAnnouncements)
                .countExpiredAnnouncements(countExpiredAnnouncements)
                .build();
    }

    public UserProfileResponse getUserProfile(String currentPhoneNumber) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        return usersMapper.toUsersProfileResponse(user);
    }

    public UserProfileResponse editUserProfile(String currentPhoneNumber, UserEditRequest userEditRequest) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        user.setName(userEditRequest.getName());
        user.setSurname(userEditRequest.getSurname());
        user.setPhoneNumber(userEditRequest.getPhoneNumber());
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        usersRepository.save(user);
        return usersMapper.toUsersProfileResponse(user);
    }

    public void changePassword(String currentPhoneNumber, UserChangePasswordRequest userChangePasswordRequest) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        if (userChangePasswordRequest.getOldPassword() != null &&
                passwordEncoder.matches(userChangePasswordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userChangePasswordRequest.getNewPassword()));
            usersRepository.save(user);
        } else {
            throw new InvalidEmailOrPasswordException("Password does not match");
        }
    }

    public List<CardsResponse> getUserCards(String currentPhoneNumber) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        List<Cards> cardsList = cardsRepository.findAllByIdIn(user.getCardsId());
        return cardsList.stream()
                .map(cardsMapper::toCardResponse)
                .collect(Collectors.toList());
    }

    public void addCard(CardRequest cardRequest, String currentPhoneNumber) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        Cards card = new Cards();
        card.setUserId(user.getId());
        card.setCardNumber(cardRequest.getCardNumber());
        card.setCVV(cardRequest.getCVV());
        card.setExpiredDate(cardRequest.getExpiredDate());
        cardsRepository.save(card);
    }

    public void deleteCard(String currentPhoneNumber, String id) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        if (user.getCardsId().contains(id)) {
            cardsRepository.deleteById(id);
        } else {
            throw new CardsNotFoundException("Card does not exist");
        }
    }

    public void deleteAccount(String currentPhoneNumber, UserDeleteRequest userDeleteRequest) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        if (passwordEncoder.matches(userDeleteRequest.getPassword(), user.getPassword())) {
            usersRepository.delete(user);
            if (announcementRepository.existsByUserId(user.getId())) {
                announcementRepository.deleteByUserId(user.getId());
            }
            if (cardsRepository.existsByUserId(user.getId())) {
                cardsRepository.deleteByUserId(user.getId());
            }
        }
    }
}
