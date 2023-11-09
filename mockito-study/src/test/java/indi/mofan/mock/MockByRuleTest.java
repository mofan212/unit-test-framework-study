package indi.mofan.mock;

import indi.mofan.entity.Account;
import indi.mofan.helloworld.dao.AccountDao;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * @author mofan 2020/12/18
 * @deprecated @Rule 注解只在 Junit4 存在，拥抱 Junit5 后，不再适用
 */
public class MockByRuleTest {

    /*
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
     */

    @Mock
    AccountDao accountDao;

    @Test
    @Disabled
    public void testMock() {
        // AccountDao accountDao = Mockito.mock(AccountDao.class);
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
