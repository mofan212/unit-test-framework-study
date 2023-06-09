package indi.mofan.mock;

import indi.mofan.helloworld.dao.AccountDao;
import indi.mofan.entity.Account;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author mofan 2020/12/18
 */
public class MockByRuleTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    AccountDao accountDao;

    @Test
    public void testMock() {
        // AccountDao accountDao = Mockito.mock(AccountDao.class);
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
