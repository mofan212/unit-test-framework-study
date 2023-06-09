package indi.mofan.mock;

import indi.mofan.dao.UserDao;
import indi.mofan.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author mofan
 * @date 2022/9/11 21:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testDoSomething() {
        UserDao userDaoMock = Mockito.mock(UserDao.class);
        Mockito.doNothing().when(userDaoMock).doSomething();
        // 使用 Spring 的测试工具类注入非 public 的字段
        ReflectionTestUtils.setField(userService, "userDao", userDaoMock);

        try {
            userService.doSomething();
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
