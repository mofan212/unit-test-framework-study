package indi.mofan.local.service;

import indi.mofan.entity.User;
import indi.mofan.local.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author mofan 2020/12/23
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class LocalUserServiceTest {

    @Test
    public void testQueryUserCount() {
        try {
            UserService userService = new UserService();
            UserDao userDao = PowerMockito.mock(UserDao.class);
            // 使用无参构造函数 new 一个 UserDao 时，总是会返回 mock 出的 userDao
            PowerMockito.whenNew(UserDao.class).withNoArguments().thenReturn(userDao);
            PowerMockito.doReturn(10).when(userDao).getCount();

            int result = userService.queryUserCount();
            Assert.assertEquals(10, result);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testSaveUser() {
        try {
            User user = new User();
            UserService userService = new UserService();
            UserDao userDao = PowerMockito.mock(UserDao.class);

            PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
            PowerMockito.doNothing().when(userDao).insertUser(user);

            userService.saveUser(user);
            Mockito.verify(userDao, Mockito.times(1)).insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
