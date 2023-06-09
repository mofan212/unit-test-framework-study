package indi.mofan.helloworld.service;

import indi.mofan.helloworld.dao.UserDao;
import indi.mofan.entity.User;

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
