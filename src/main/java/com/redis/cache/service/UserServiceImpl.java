package com.redis.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.cache.model.User;
import com.redis.cache.repository.UserRepository;

/**
 * @author VenkatS
 *
 */
@Service
@CacheConfig
public class UserServiceImpl implements UserService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	  private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	
	@Override
	@Cacheable(cacheNames ="redis1", key = "#userId", unless = "#result.followers < 12000")
    public User findById(Long id) {
		LOG.info("Getting UserServiceImpl with ID {}.", id);
		 return userRepository.findById(id).get();
	}

	@Override
	@CachePut(cacheNames = "redis1", key = "#user.id")
    public User updatePersonByID(User usr) {
		
		return userRepository.save(usr);
	}

	@Override
	@CacheEvict(value = "redis1", allEntries=true)
    public void deleteById(Long id) {
		userRepository.deleteById(id);

	}

}
