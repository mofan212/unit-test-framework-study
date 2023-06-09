package indi.mofan.spy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author mofan 2020/12/24
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceSpyTest {

    @Test
    public void testFoo() {
        UserService userService = PowerMockito.spy(new UserService());
        System.out.println(userService);
        String arg = "hello";
        PowerMockito.doNothing().when(userService).foo(arg);

        userService.foo(arg);
        userService.foo("world");
    }

    @Test
    public void testCheck() throws Exception {
        UserService userService = PowerMockito.spy(new UserService());

        /*
         * 下述 when() 方法参数解析:
         * 第一个参数: 对象
         * 第二个参数: 对象的方法
         * 第三个及以后的参数: 方法的参数列表
         */
        PowerMockito.doReturn(true).when(userService, "checkExist", "mofan");
        Assert.assertTrue(userService.exist("mofan"));

        try {
            userService.exist("any");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}