package indi.mofan.controller;

import indi.mofan.helloworld.dao.AccountDao;
import indi.mofan.entity.Account;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mofan 2020/12/18
 */
public class AccountLoginController {

    private final AccountDao accountDao;

    public AccountLoginController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 模拟用户登录
     * @return 界面名称
     */
    public String login(HttpServletRequest request) {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        try {
            Account account = accountDao.findAccount(username, password);
            if (account == null) {
                return "/login";
            } else {
                return "/index";
            }
        } catch (Exception e) {
            return "/505";
        }
    }
}
