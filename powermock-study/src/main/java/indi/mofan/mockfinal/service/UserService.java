package indi.mofan.mockfinal.service;

import indi.mofan.entity.User;
import indi.mofan.mockfinal.dao.UserDao;

/**
 * @author mofan 2020/12/22
 */
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int queryUserCount() {
        return userDao.getCount();
    }

    public void saveUser(User user) {
        userDao.insertUser(user);
    }
}
