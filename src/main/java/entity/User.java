package entity;

/**
 * The representation of a user in our program.
 */
public interface User {
    String getUserId();
    String getName();
    void setName(String name);
    int getAge();
    void setAge(int age);
    String getInterests();
    void setInterests(String interests);
    String getPassword();
}

