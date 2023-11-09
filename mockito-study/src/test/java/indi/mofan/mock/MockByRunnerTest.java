package indi.mofan.mock;

import indi.mofan.entity.Account;
import indi.mofan.helloworld.dao.AccountDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author mofan 2020/12/18
 */
@ExtendWith(MockitoExtension.class)
public class MockByRunnerTest {
    @Test
    public void testMock() {
        AccountDao accountDao = Mockito.mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS);
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
