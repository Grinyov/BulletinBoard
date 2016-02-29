package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Advert;

import java.util.List;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
public interface AdvertDao {
    List<Advert> getAllAds();
    List<Advert> getAdsByCategoryId();
    boolean addAdvert();
    List<Advert> searchAdvert(String pattern);
    boolean deleteAdvert(long id);
}