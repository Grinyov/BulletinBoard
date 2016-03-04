package com.grinyov.bulletinboard.dao;

import com.grinyov.bulletinboard.model.Category;

import java.util.List;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
public interface CategoryDao {

    List<Category> getAllCategories();

    Category getCategoryById(int categoryid);
}
