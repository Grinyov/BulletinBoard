package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao{

    private EntityManager em;

    private EntityManager getEm() {
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BulletinBoard");
            em = emf.createEntityManager();
        }
        return em;
    }

    @Override
    public boolean addAccount(Account account) {
        try {
            getEm().getTransaction().begin();
            getEm().persist(account);
            getEm().getTransaction().commit();
            return true;
        } catch (Exception e) {
            getEm().getTransaction().rollback();
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Account getAccountByName(String name) {
            TypedQuery<Account> query = getEm().createNamedQuery("Account.findByPhone", Account.class);
            query.setParameter("name", name);
            List<Account> accounts = query.getResultList();
            return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (em != null)
            em.close();
    }
}
