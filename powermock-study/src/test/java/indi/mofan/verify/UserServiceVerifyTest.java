package indi.mofan.verify;

import indi.mofan.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author mofan 2020/12/24
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceVerifyTest {

    @Test
    public void testSaveOrUpdateWillUseNewJoiner() throws Exception {
        User user = PowerMockito.mock(User.class);
        UserDao userDao = PowerMockito.mock(UserDao.class);

        PowerMockito.whenNew(UserDao.class).withNoArguments().thenReturn(userDao);
        PowerMockito.when(userDao.getCount()).thenReturn(0);

        UserService userService = new UserService();
        userService.saveOrUpdate(user);

        Mockito.verify(userDao).insertUser(user);
        Mockito.verify(userDao, Mockito.never()).updateUser(user);
    }

    @Test
    public void testSaveOrUpdateWillUseUpdateOriginal() throws Exception {
        User user = PowerMockito.mock(User.class);
        UserDao userDao = PowerMockito.mock(UserDao.class);

        PowerMockito.whenNew(UserDao.class).withNoArguments().thenReturn(userDao);
        PowerMockito.when(userDao.getCount()).thenReturn(1);

        UserService userService = new UserService();
        userService.saveOrUpdate(user);

        Mockito.verify(userDao, Mockito.never()).insertUser(user);
        Mockito.verify(userDao).updateUser(user);
    }
}
