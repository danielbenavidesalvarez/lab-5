package entity;

/**
 * A User interface that defines the structure of user entities.
 */
public interface User {
    String getName();

    void setUserId(String userid);

    String getPassword();

    // Methods for additional fields
    int getAge();
    void setAge(int age);

    String getInterests();
    void setInterests(String interests);

    void setName(String name);

    String getUserId();
}
