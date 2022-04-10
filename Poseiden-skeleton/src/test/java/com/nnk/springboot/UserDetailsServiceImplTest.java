package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.implement.UserDetailsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void userDetailsServiceImplTest() {

        User user = new User("Sarah", "Ssarah2022$", "SarahFullname", "User");
        userRepository.save(user);


        // getUserByUsername
        User findUser = userRepository.getUserByUsername(user.getUsername());
        Assert.assertNotNull(findUser);

        // getUserAuthorities
        List<GrantedAuthority> grantedAuthorities = userDetailsService.getUserAuthority(user.getRole());
        Assert.assertFalse(grantedAuthorities.isEmpty());

        // buildUserForAuthentication
        UserDetails userDetails = userDetailsService.buildUserForAuthentication(user, grantedAuthorities);
        Assert.assertNotNull(userDetails);
        Assert.assertEquals(userDetails.getUsername(), user.getUsername());
    }
}
