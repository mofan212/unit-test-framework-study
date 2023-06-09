package indi.mofan.mockstatic.service;

import indi.mofan.entity.User;
import indi.mofan.mockstatic.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author mofan 2020/12/23
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserDao.class)
public class StaticUserServiceTest {
    @Test
    public void testQueryUserCount() {
        PowerMockito.mockStatic(UserDao.class);
        PowerMockito.when(UserDao.getCount()).thenReturn(10);

        UserService userService = new UserService();
        int result = userService.queryUserCount();
        Assert.assertEquals(10, result);
    }

    @Test
    public void testSaveUser() {
        PowerMockito.mockStatic(UserDao.class);
        User user = new User();
        PowerMockito.doNothing().when(UserDao.class);

        UserService userService = new UserService();
        userService.saveUser(user);
    }
}
