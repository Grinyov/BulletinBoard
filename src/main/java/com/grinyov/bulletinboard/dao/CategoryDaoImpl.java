package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Category;
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
@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao{

    private EntityManager em;

    private EntityManager getEm() {
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BulletinBoard");
            em = emf.createEntityManager();
        }
        return em;
    }

    @Override
    public List<Category> getAllCategories() {
        return getEm().createNamedQuery("Category.findAll", Category.class).getResultList();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        TypedQuery<Category> query = getEm().createNamedQuery("Category.findById", Category.class);
        query.setParameter("categoryId", categoryId);
        List<Category> categories = query.getResultList();
        return categories.isEmpty() ? null : categories.get(0);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (em != null)
            em.close();
    }
}
