package indi.mofan.helloworld.dao;

import indi.mofan.entity.Account;

/**
 * @author mofan 2020/12/18
 */
public class AccountDao {

    public Account findAccount(String username, String password) {
        // 假设此时 DB 不可用
        throw new UnsupportedOperationException();
    }
}
