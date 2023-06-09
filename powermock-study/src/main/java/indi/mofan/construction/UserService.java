package indi.mofan.construction;

/**
 * @author mofan 2020/12/24
 */
public class UserService {

    public void save(String username, String password) {
        UserDao userDao = new UserDao(username, password);
        userDao.insert();
    }
}
