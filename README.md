# CarSales-API

**CarSales-API** is a RESTful backend application for a car sales platform. The API supports both **admin** and **user** operations through a unified system. 
It allows users to post car listings, view ads, manage profiles, and interact with bookmarked or expired listings. Admins can manage announcements, assist users, and moderate the platform.

---

## 🎯 Project Objectives

- Provide a single API endpoint for both admin and user functionalities.
- Enable secure and role-based access using Spring Security and JWT.
- Offer full CRUD capabilities for car listings, user profiles, bookmarks, and support requests.

---

## 🚀 Main Features

### ✅ **AuthService**
- `signUpUser`: Registers a new user.
- `loginUser`: Authenticates users and returns a JWT token.

### 📣 **AnnouncementService**
- `getAllUserAnnouncement`: Lists all announcements by a user.
- `getPublishedAnnouncements`: Shows all published ads.
- `getExpiredAnnouncements`: Lists expired ads.
- `getRejectedAnnouncements`: Lists rejected ads.
- `addNewAnnouncement`: Adds a new car listing.

### 🧾 **AdminService**
- `getAnnouncement`: View all ads for moderation.
- `checkAds`: Approve or reject announcements.
- `helpUser`: Respond to user support requests.
- `getHelpUser`: View all incoming help requests.

### 🚗 **AutosService**
- `getAnnouncement`: Fetch only active announcements.
- `addCarInBookmarks`: Add a car listing to the user's bookmarks.

### 🔖 **BookmarksService**
- `getMarkedAnnouncement`: View all bookmarked ads.
- `removeMarkedAnnouncement`: Remove a car from bookmarks.

### 🙋‍♂️ **UsersService**
- `getAccountInfo`: Get current account details.
- `getUserProfile`: View public user profile.
- `editUserProfile`: Update user profile info.
- `changePassword`: Change the user’s password.
- `getUserCards`: View cards linked to a user.
- `addCard`: Add a new card to the account.
- `deleteCard`: Delete a specific card.
- `deleteAccount`: Delete user account permanently.

### 🆘 **HelpService**
- `sendHelpRequest`: Allows users to submit a support request.

---

## 🛠 Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Stream API**
- **PostgreSQL**
- **MongoDB**
- **Pagination**
- **Swagger UI**
- **JUnit & Mockito**

---
