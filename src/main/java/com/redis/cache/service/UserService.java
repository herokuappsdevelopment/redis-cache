package com.redis.cache.service;

import com.redis.cache.model.User;


/**
 * @author VenkatS
 *
 */
public interface UserService {
	
	User findById(Long id);
	
	User updatePersonByID(User usr);
	
	void deleteById(Long id);

}
