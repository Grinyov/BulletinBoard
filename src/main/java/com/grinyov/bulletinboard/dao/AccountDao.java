package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Account;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */

public interface AccountDao {

    boolean addAccount(Account account);

    Account getAccountByName(String name);

}
