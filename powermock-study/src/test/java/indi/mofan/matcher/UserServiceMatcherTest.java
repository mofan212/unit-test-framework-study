package indi.mofan.matcher;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * @author mofan 2020/12/24
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceMatcherTest {

    @Ignore
    @Test
    public void testFind() throws Exception {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.queryByName("mofan")).thenReturn("默烦");
        UserService userService = new UserService();
        String result = userService.find("mofan");
        Assert.assertEquals("默烦", result);

        // 没有匹配的参数，因此测试不会通过
        String yang = userService.find("yang");
        Assert.assertEquals("默烦", yang);
    }

    @Test
    public void testFindWithMatcher() throws Exception {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.queryByName(Mockito.argThat(new MyArgumentMatcher()))).thenReturn("默烦");
        UserService userService = new UserService();

        Assert.assertEquals("默烦", userService.find("mofan"));
        Assert.assertEquals("默烦", userService.find("yang"));
    }

    @Test
    public void testFindWithAnswer() throws Exception {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.queryByName(Mockito.anyString())).then(invocation -> {
            String arg = (String) invocation.getArguments()[0];
            return switch (arg) {
                case "mofan" -> "I am mofan.";
                case "yang" -> "I am Yang.";
                default -> throw new RuntimeException("Not support " + arg);
            };
        });

        UserService userService = new UserService();
        Assert.assertEquals("I am mofan.", userService.find("mofan"));
        Assert.assertEquals("I am Yang.", userService.find("yang"));

        try {
            String anyValue = userService.find("anyValue");
            Assert.fail("never process to here is right.");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
        }
    }

    static class MyArgumentMatcher implements ArgumentMatcher<String> {
        public boolean matches(String s) {
            return switch (s) {
                case "mofan", "yang" -> true;
                default -> false;
            };
        }
    }

}