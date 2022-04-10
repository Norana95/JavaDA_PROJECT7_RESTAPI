package com.nnk.springboot.service.implement;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(userName);
        logger.info("get user done !");
        List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
        logger.info("get role of user done !");
        return buildUserForAuthentication(user, authorities);
    }

    public UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> grantedAuthorities) {
        logger.info("inside methode buildUserForAuthentication in UserDetailsServiceImpl");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public List<GrantedAuthority> getUserAuthority(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        logger.info("add role");
        return grantedAuthorities;
    }

    public String getUserFromPrincipal(Principal loggedInUser) {
        if (loggedInUser instanceof OAuth2AuthenticationToken) {
            //extraire les infos user
            OAuth2User principal = ((OAuth2AuthenticationToken) loggedInUser).getPrincipal();
            return   principal.getAttributes().get("login").toString();
        }
        else {
            return loggedInUser.getName();
        }
    }

}
