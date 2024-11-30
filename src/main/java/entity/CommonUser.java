package entity;

import java.util.HashSet;
import java.util.Set;

public class CommonUser implements User {

    private String name;
    private final String password;
    private int age;
    private String interests;
    private String userId;
    private final Set<String> likedUsers = new HashSet<>();
    private int likesReceivedCount; // Tracks the number of likes received by this user.

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void likeUser(User likedUser) {
        if (likedUser == null || likedUser.getUserId() == null) {
            throw new IllegalArgumentException("Liked user must have a valid user ID");
        }
        if (likedUser.getUserId().equals(this.userId)) {
            throw new IllegalArgumentException("Users cannot like themselves");
        }
        if (!likedUsers.add(likedUser.getUserId())) {
            throw new IllegalStateException("User already liked this person");
        }
        if (likedUser instanceof CommonUser) {
            ((CommonUser) likedUser).incrementLikesReceived();
        }
    }

    private void incrementLikesReceived() {
        this.likesReceivedCount++;
    }

    public int getLikesReceivedCount() {
        return likesReceivedCount;
    }

    public Set<String> getLikedUsers() {
        return likedUsers;
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
        this.userId = userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

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
