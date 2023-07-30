public class User {
    private String userId;
    private String password;

/*
Developed by Jaya Sri S
 */

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
