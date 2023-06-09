package indi.mofan.verify;

import indi.mofan.entity.User;

/**
 * @author mofan 2020/12/24
 */
public class UserService {

    public void saveOrUpdate(User user) {
        UserDao userDao = new UserDao();

        if (userDao.getCount() > 0) {
            userDao.updateUser(user);
        } else {
            userDao.insertUser(user);
        }
    }
}
