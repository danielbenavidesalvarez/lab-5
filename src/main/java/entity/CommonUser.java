package entity;

/**
 * A simple implementation of the User interface with additional fields for age and interests.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private int age;
    private String interests;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getUserId() {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {

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
        this.age = age;
    }

    // Getter and Setter for interests
    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
    }
}
