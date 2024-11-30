package entity;

import java.util.Set;

/**
 * A User interface that defines the structure of user entities.
 */
public interface User {
    String getName();

    void setUserId(String userId);

    String getPassword();

    // Methods for additional fields
    int getAge();
    void setAge(int age);

    String getInterests();
    void setInterests(String interests);

    void setName(String name);

    String getUserId();

    void likeUser(User likedUser);

    // New methods for analytics
    Set<String> getLikedUsers();
    int getLikesReceivedCount();
}

