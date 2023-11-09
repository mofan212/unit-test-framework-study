package indi.mofan.mock;

import indi.mofan.entity.User;
import indi.mofan.helloworld.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author mofan 2020/12/18
 */
public class DeepMockTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private UserService userService;

    private AutoCloseable closeable;

    @BeforeEach
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void destroy() throws Exception {
        closeable.close();
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
