package com.grinyov.bulletinboard.dao;

import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
public interface UserDao {

    public void insertOrUpdate(User user);

    public void delete(String username);

    public User get(String username);

    public List<User> list();

}
