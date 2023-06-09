package indi.mofan.mock;

import indi.mofan.entity.Account;
import indi.mofan.helloworld.dao.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author mofan 2020/12/18
 */
@RunWith(MockitoJUnitRunner.class)
public class MockByRunnerTest {
    @Test
    public void testMock() {
        AccountDao accountDao = Mockito.mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS);
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
