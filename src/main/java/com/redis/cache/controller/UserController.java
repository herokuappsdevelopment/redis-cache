package com.redis.cache.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redis.cache.mapper.UserMapper;
import com.redis.cache.model.User;

/**
 * @author VenkatS
 *
 */
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

     @Autowired
     UserMapper mapper;

    @Cacheable(value = "redis1", key = "#userId")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        logger.info("Getting user with ID {}.", userId);
        return mapper.findById(Integer.valueOf(userId));	
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getUsers() {
        return mapper.findAll();	
    }

    @CachePut(value = "redis1", key = "#user.id")
    @PutMapping("/update")
    public User updatePersonByID(@RequestBody User user) {
        mapper.updateUserByID(user);
        return user;
    }
    
    @CachePut(value = "redis1", key = "#user.id")
    @PostMapping("/insert")
    public User insertUser(@RequestBody User user) {
        mapper.insertUser(user);
        return user;
    }

    @CacheEvict(value = "redis1", allEntries=true)
    @DeleteMapping("/{userId}")
    public void deleteUserByID(@PathVariable Integer userId) {
        logger.info("deleting person with id {}", userId);
        mapper.deleteById(userId);
    }
}
