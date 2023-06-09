package indi.mofan.construction;

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
public class UserServiceConstructionTest {

    @Test
    public void testSave() throws Exception {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        String username = "mofan";
        String password = "123456";

        PowerMockito.whenNew(UserDao.class).withArguments(username, password).thenReturn(userDao);
        PowerMockito.doNothing().when(userDao).insert();

        UserService userService = new UserService();
        // 如果 save() 的参数不是 username 和 password，测试将不会通过
        userService.save(username, password);
        Mockito.verify(userDao).insert();
    }
}