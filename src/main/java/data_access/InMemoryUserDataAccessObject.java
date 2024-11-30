package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.CommonUser;
import entity.User;
import use_case.analytics.AnalyticsUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.edit_profile.UserDataAccessInterface; // Import the correct interface
import use_case.login.LoginUserDataAccessInterface;
import use_case.like.LikeUserDataAccessInterface; // Import LikeUserDataAccessInterface
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        UserDataAccessInterface, // Add implementation for Edit Profile
        LikeUserDataAccessInterface,
        AnalyticsUserDataAccessInterface {


    private final Map<String, User> users = new HashMap<>();
    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user); // Save user data
    }

    @Override
    public User get(String username) {
        return users.get(username); // Retrieve user data
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    // Implement Edit Profile's UserDataAccessInterface
    @Override
    public User findById(String userId) {
        return users.get(userId); // Find user by ID
    }

    @Override
    public User getUserById(String userId) {
        System.out.println("Data Access: Fetching user by ID: " + userId);

        // Create a dummy user with a valid userId
        CommonUser user = new CommonUser("John Doe", "password123");
        user.setUserId(userId);

        // Create a liked user with a valid userId
        CommonUser likedUser = new CommonUser("Jane Smith", "password123");
        likedUser.setUserId("liked_user_123");

        // Add the liked user to this user's likes
        user.likeUser(likedUser);

        return user;
    }

    @Override
    public int countMatches(String userId) {
        System.out.println("Data Access: Counting matches for user ID: " + userId);
        return 1; // Dummy data
    }

    @Override
    public int countSharedInterests(String userId) {
        System.out.println("Data Access: Counting shared interests for user ID: " + userId);
        return 4; // Dummy data
    }
    public int countLikesGiven(String userId){
        System.out.println("Data Access: Counting shared interests for user ID: " + userId);
        return 10; // Dummy data
    }

    @Override
    public int countLikesReceived(String userId) {
        return 2;
    }
}
