package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Advert;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<Advert> getAllAds() {
        return null;
    }

    @Override
    public List<Advert> getAdsByCategoryId() {
        return null;
    }

    @Override
    public boolean addAdvert() {
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
