package indi.mofan.mockstatic.service;

import indi.mofan.entity.User;
import indi.mofan.mockstatic.dao.UserDao;

/**
 * @author mofan 2020/12/22
 */
public class UserService {
    public int queryUserCount() {
        return UserDao.getCount();
    }

    public void saveUser(User user) {
        UserDao.insertUser(user);
    }
}
