package indi.mofan.helloworld.service;

import indi.mofan.helloworld.dao.UserDao;
import indi.mofan.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

/**
 * @author mofan 2020/12/22
 */
public class UserServiceTest {

    private UserService userService;

    @Before
    public void setup() {
        userService = new UserService(new UserDao());
    }

    @Mock
    private UserDao userDao;

    @Test
    public void testQueryUserCountWithPowerMock() {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.when(userDao.getCount()).thenReturn(10);
        UserService userService = new UserService(userDao);

        int result = userService.queryUserCount();
        Assert.assertEquals(10, result);
    }

    @Test
    public void testSaveUserWithPowerMock() {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        User user = new User();
        PowerMockito.doNothing().when(userDao).insertUser(user);
        UserService userService = new UserService(userDao);
        userService.saveUser(user);
        // 验证 insertUser() 方法被调用一次
        Mockito.verify(userDao).insertUser(user);
    }

    @Ignore
    @Test
    public void testQueryUserCountWithMockito() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserService(userDao);
        Mockito.when(userDao.getCount()).thenReturn(10);

        int result = userService.queryUserCount();
        Assert.assertEquals(10, result);
    }

    @Ignore
    @Test
    public void testQueryUserCountWithJunit() {
        try {
            int count = userService.queryUserCount();
            Assert.fail("should not process to here");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    @Ignore
    @Test
    public void testSaveUserWithJunit() {
        try {
            userService.saveUser(new User());
            Assert.fail("should not process to here");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}
