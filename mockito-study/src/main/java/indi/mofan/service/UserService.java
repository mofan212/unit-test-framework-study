package indi.mofan.service;

import indi.mofan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mofan
 * @date 2022/9/11 21:51
 */
@Component
public class UserService {
    @Autowired
    private UserDao userDao;

    public void doSomething() {
        userDao.doSomething();
    }
}
