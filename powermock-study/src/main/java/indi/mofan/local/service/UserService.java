package indi.mofan.local.service;

import indi.mofan.entity.User;
import indi.mofan.local.dao.UserDao;

/**
 * @author mofan 2020/12/22
 */
public class UserService {
    public int queryUserCount() {
        UserDao userDao = new UserDao();
        return userDao.getCount();
    }

    public void saveUser(User user) {
        UserDao userDao = new UserDao();
        userDao.insertUser(user);
    }
}
