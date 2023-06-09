package indi.mofan.mock;

import indi.mofan.entity.User;
import indi.mofan.helloworld.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author mofan 2020/12/18
 */
public class DeepMockTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * stubbling
     */
    @Test
    public void testDeepMock() {
        User user = userService.get();
        user.foo();
    }
}
