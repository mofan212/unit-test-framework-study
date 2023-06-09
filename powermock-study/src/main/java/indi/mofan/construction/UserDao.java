package indi.mofan.construction;

/**
 * @author mofan 2020/12/24
 */
public class UserDao {
    private String username;
    private String password;

    public UserDao(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void insert() {
        throw new UnsupportedOperationException();
    }
}
