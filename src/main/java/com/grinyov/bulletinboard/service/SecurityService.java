package com.grinyov.bulletinboard.service;

import com.grinyov.bulletinboard.dao.AccountDao;
import com.grinyov.bulletinboard.exception.NoSuchAccountException;
import com.grinyov.bulletinboard.model.Account;
import com.grinyov.bulletinboard.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
@Service("securityService")
public class SecurityService implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws NoSuchAccountException {

        Account account = accountDao.get(name);
        // roles
        Set<GrantedAuthority> roles = new HashSet();
        if (account.getName().equals("admin")){
            roles.add(new SimpleGrantedAuthority(UserRole.ADMIN.name()));
        }else {
            roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));
        }
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(account.getName(),
                        account.getPassword(),
                        roles);

        return userDetails;
    }
}