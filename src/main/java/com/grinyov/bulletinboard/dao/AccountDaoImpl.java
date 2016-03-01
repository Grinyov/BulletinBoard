package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Account;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao{

    @Autowired
    private SessionFactory sessionFactory;

    public AccountDaoImpl() {}

    public AccountDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void insertOrUpdate(Account account) {
        sessionFactory.getCurrentSession().saveOrUpdate(account);
    }

    @Override
    @Transactional
    public void delete(int id) {

Account accountToDelete = new Account();
		accountToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(accountToDelete);

    }

    @Override
    @Transactional
    public Account get(String name) {
        return (Account) sessionFactory.getCurrentSession().getNamedQuery("Account.findByName")
                .setParameter("name", name).uniqueResult();
    }

    @Override
    @Transactional
    public List<Account> list() {

        @SuppressWarnings("unchecked")
		List<Account> listAccount = (List<Account>) sessionFactory.getCurrentSession()
				.createCriteria(Account.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listAccount;
    }
}
