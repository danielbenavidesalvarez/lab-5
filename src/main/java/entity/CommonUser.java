package entity;

/**
 * A simple implementation of the User interface with additional fields for age and interests.
 */
public class CommonUser implements User {

    private String name;
    private final String password;
    private int age;
    private String interests;
    private String userid;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getUserId() {
        return userid;
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
