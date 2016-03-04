package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Advert;

import java.util.List;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
public interface AdvertDao {
    List<Advert> getAllAdverts();

    List<Advert> getTopAdverts();

    List<Advert> getAdvertsByCategoryId(int categoryId);

    boolean addAdvert(Advert Advert);

    List<Advert> searchAdvert(String pattern);

    boolean deleteAdvert(long id);

    void deleteAdverts(long[] ids);
}
