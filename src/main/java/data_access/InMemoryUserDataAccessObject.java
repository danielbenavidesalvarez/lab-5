package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
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
        LikeUserDataAccessInterface {


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
}
