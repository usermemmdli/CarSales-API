package com.example.Car_SalesAPI.controller;

import com.example.Car_SalesAPI.dto.request.CardRequest;
import com.example.Car_SalesAPI.dto.request.UserChangePasswordRequest;
import com.example.Car_SalesAPI.dto.request.UserDeleteRequest;
import com.example.Car_SalesAPI.dto.request.UserEditRequest;
import com.example.Car_SalesAPI.dto.response.AccountResponse;
import com.example.Car_SalesAPI.dto.response.CardsResponse;
import com.example.Car_SalesAPI.dto.response.UserProfileResponse;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import com.example.Car_SalesAPI.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final AuthenticationHelperService authenticationHelperService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<AccountResponse> getAccountInfo() {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        AccountResponse accountResponse = usersService.getAccountInfo(currentPhoneNumber);
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<UserProfileResponse> getUserProfile() {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        UserProfileResponse userProfileResponse = usersService.getUserProfile(currentPhoneNumber);
        return ResponseEntity.ok(userProfileResponse);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<UserProfileResponse> editUserProfile(@RequestBody UserEditRequest userEditRequest) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        UserProfileResponse userProfileResponse = usersService.editUserProfile(currentPhoneNumber, userEditRequest);
        return ResponseEntity.ok(userProfileResponse);
    }

    @PatchMapping("/edit/password")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody UserChangePasswordRequest userChangePasswordRequest) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        usersService.changePassword(currentPhoneNumber, userChangePasswordRequest);
    }

    @GetMapping("/user-cards")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<List<CardsResponse>> getUserCards() {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        List<CardsResponse> cardsResponse = usersService.getUserCards(currentPhoneNumber);
        return ResponseEntity.ok(cardsResponse);
    }

    @PostMapping("/user-cards/add")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCard(@RequestBody CardRequest cardRequest) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        usersService.addCard(cardRequest, currentPhoneNumber);
    }

    @DeleteMapping("/user-cards")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCard(@RequestParam String id) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        usersService.deleteCard(currentPhoneNumber, id);
    }

    @DeleteMapping("/edit")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@RequestBody UserDeleteRequest userDeleteRequest) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        usersService.deleteAccount(currentPhoneNumber, userDeleteRequest);
    }
}
