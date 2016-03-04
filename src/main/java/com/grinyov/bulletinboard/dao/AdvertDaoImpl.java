package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Advert;
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
@Repository("advertDao")
public class AdvertDaoImpl implements AdvertDao{

    private EntityManager em;

    private EntityManager getEm() {
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BulletinBoard");
            em = emf.createEntityManager();
        }
        return em;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Advert> getAllAdverts() {
        return getEm().createNamedQuery("Advert.findAll", Advert.class).getResultList();
    }

    @Override
    public List<Advert> getTopAdverts() {
        TypedQuery<Advert> query = getEm().createNamedQuery("Advert.findTop", Advert.class);
        return query.getResultList();
    }

    @Override
    public List<Advert> getAdvertsByCategoryId(int categoryId) {
        TypedQuery<Advert> query = getEm().createNamedQuery("Advert.findByCategory", Advert.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }


    @Override
    public boolean addAdvert(Advert Advert) {
        try {
            getEm().getTransaction().begin();
            getEm().persist(Advert);
            getEm().getTransaction().commit();
            return true;
        } catch (Exception e) {
            getEm().getTransaction().rollback();
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Advert> searchAdvert(String pattern) {
        TypedQuery<Advert> query = getEm().createNamedQuery("Advert.findByPattern", Advert.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList();
    }

    @Override
    public boolean deleteAdvert(long id) {
        try {
            getEm().getTransaction().begin();
            Advert Advert = getEm().find(Advert.class, id);
            if (Advert != null) {
                getEm().remove(Advert);
            }
            getEm().getTransaction().commit();
            return true;
        } catch (Exception e) {
            getEm().getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteAdverts(long[] ids) {
        try {
            getEm().getTransaction().begin();
            Advert Advert;
            for (long idx : ids) {
                Advert = getEm().find(Advert.class, idx);
                if (Advert != null) {
                    getEm().remove(Advert);
                }
            }
            getEm().getTransaction().commit();
        } catch (Exception e) {
            getEm().getTransaction().rollback();
            e.printStackTrace();
        }
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (em != null)
            em.close();
    }
}
