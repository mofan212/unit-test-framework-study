package indi.mofan.mock;

import indi.mofan.entity.Account;
import indi.mofan.helloworld.dao.AccountDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author mofan 2020/12/18
 */
public class MockByStaticMethodTest {
    @Test
    public void testMock() {
        AccountDao accountDao = Mockito.mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS);
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
