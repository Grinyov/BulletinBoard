package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Account;

import java.util.List;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */

public interface AccountDao {

    public void insertOrUpdate(Account account);

    public void delete(int id);

    public Account get(String name);

    public List<Account> list();

}
