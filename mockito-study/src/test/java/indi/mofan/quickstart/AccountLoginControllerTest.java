package indi.mofan.quickstart;

import indi.mofan.controller.AccountLoginController;
import indi.mofan.entity.Account;
import indi.mofan.helloworld.dao.AccountDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;


/**
 * @author mofan 2020/12/18
 */
@ExtendWith(MockitoExtension.class)
public class AccountLoginControllerTest {
    private AccountDao accountDao;
    private HttpServletRequest request;
    private AccountLoginController accountLoginController;

    @BeforeEach
    public void before() {
        // 测试方法执行前 Mock 数据
        this.accountDao = Mockito.mock(AccountDao.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        this.accountLoginController = new AccountLoginController(accountDao);
    }

    @Test
    public void testLoginSuccess() {
        Account account = new Account();
        Mockito.when(request.getParameter("username")).thenReturn("mofan");
        Mockito.when(request.getParameter("password")).thenReturn("123456");
        Mockito.when(accountDao.findAccount(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(account);
        Assertions.assertEquals(accountLoginController.login(request), "/index");
    }

    @Test
    public void testLoginFailure() {
        Mockito.when(request.getParameter("username")).thenReturn("默烦");
        Mockito.when(request.getParameter("password")).thenReturn("147258");
        // 指定 findAccount() 方法返回 null
        Mockito.when(accountDao.findAccount(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(null);
        // 返回 /login
        Assertions.assertEquals(accountLoginController.login(request), "/login");
    }


    @Test
    public void testLogin505() {
        Mockito.when(request.getParameter("username")).thenReturn("404");
        Mockito.when(request.getParameter("password")).thenReturn("500");
        // 指定 findAccount() 方法返回 null
        Mockito.when(accountDao.findAccount(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenThrow(UnsupportedOperationException.class);
        // 返回 /login
        Assertions.assertEquals(accountLoginController.login(request), "/505");
    }
}
