package indi.mofan.mock;

import indi.mofan.entity.Account;
import indi.mofan.helloworld.dao.AccountDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author mofan 2020/12/18
 */
public class MockByAnnotationTest {

    // @Mock(answer = Answers.RETURNS_SMART_NULLS)
    @Mock
    private AccountDao accountDao;

    private AutoCloseable closeable;

    @Before
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void destroy() throws Exception {
        closeable.close();
    }

    @Test
    public void testMock() {
        Account account = accountDao.findAccount("x", "x");
        // null
        System.out.println(account);
    }
}
