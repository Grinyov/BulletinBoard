package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Advert;
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
@Repository("advertDao")
public class AdvertDaoImpl implements AdvertDao{

    @Autowired
    private SessionFactory sessionFactory;

    public AdvertDaoImpl() {}

    public AdvertDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Advert> getAllAds() {
        @SuppressWarnings("unchecked")
        List<Advert> listAdvert = (List<Advert>) sessionFactory.getCurrentSession().createCriteria(Advert.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listAdvert;
    }

    @Override
    @Transactional
    public List<Advert> getAdsByCategoryId(int categoryId) {
        return null;
    }

    @Override
    @Transactional
    public Advert getAdvertById(int id) {
        return (Advert) sessionFactory.getCurrentSession().getNamedQuery("Advert.findById")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional
    public boolean addAdvert(Advert advert) {
        return false;
    }

    @Override
    public List<Advert> searchAdvert(String pattern) {
        return null;
    }

    @Override
    public boolean deleteAdvert(long id) {
        return false;
    }
}
