package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * A simple implementation of the User interface with additional fields for age, interests, and liked users.
 */
public class CommonUser implements User {

    private String name;
    private final String password;
    private int age;
    private String interests;
    private String userid;
    private final Set<String> likedUsers = new HashSet<>(); // Stores the IDs of liked users

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getUserId() {
        return userid;
    }

    @Override
    public void likeUser(User likedUser) {
        if (likedUser == null || likedUser.getUserId() == null) {
            throw new IllegalArgumentException("Liked user must have a valid user ID");
        }
        if (likedUser.getUserId().equals(this.userid)) {
            throw new IllegalArgumentException("Users cannot like themselves");
        }
        if (!likedUsers.add(likedUser.getUserId())) {
            throw new IllegalStateException("User already liked this person");
        }
        likedUsers.add(likedUser.getUserId());
    }

    public boolean hasLiked(String userId) {
        return likedUsers.contains(userId);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    @Override
    public void setUserId(String userId) {
        this.userid = userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Getter and Setter for age
    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    // Getter and Setter for interests
    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public void setInterests(String interests) {
        if (interests != null) {
            this.interests = interests;
        }
    }
}
