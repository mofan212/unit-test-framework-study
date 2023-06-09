package indi.mofan.mockfinal.service;

import indi.mofan.mockfinal.dao.UserDao;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * @author mofan 2020/12/23
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserDao.class)
public class FinalUserServiceTest {

    @Mock
    private UserDao userDao;

    @Ignore
    @Test
    public void testQueryUserCountWithMockito() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserService(userDao);
        Mockito.when(userDao.getCount()).thenReturn(10);
        int result = userService.queryUserCount();
        Assert.assertEquals(10, result);
    }

    @Test
    public void testQueryUserCountWithPowerMock() {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.when(userDao.getCount()).thenReturn(10);
        UserService userService = new UserService(userDao);
        int result = userService.queryUserCount();
        Assert.assertEquals(10, result);
    }
}
