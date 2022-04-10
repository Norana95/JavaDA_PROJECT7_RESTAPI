package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userTest() {
        User user = new User("Norhene", "Nnorhene2022$", "blz", "user");

        // Save
        user = userRepository.save(user);
        Assert.assertNotNull(user.getId());
        Assert.assertTrue(user.getUsername().equals("Norhene"));

        // Update
        user.setFullname("bkz");
        user = userRepository.save(user);
        Assert.assertTrue(user.getFullname().equals("bkz"));

        // Find
        List<User> userList = userRepository.findAll();
        Assert.assertTrue(userList.size() > 0);

        // Find by id
        Optional<User> userId = userRepository.findById(user.getId());
        Assert.assertEquals(userId.get().getId(), user.getId());

        // Delete
        Integer id = user.getId();
        userRepository.delete(user);
        Optional<User> optionalUser = userRepository.findById(id);
        Assert.assertFalse(optionalUser.isPresent());
    }
}
