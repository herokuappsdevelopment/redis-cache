package com.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redis.cache.model.User;
import com.redis.cache.repository.UserRepository;

/**
 * @author VenkatS
 *
 */
@SpringBootApplication
public class RedisCacheApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	@Autowired
	public RedisCacheApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {	
		SpringApplication.run(RedisCacheApplication.class, args);
	}

	@Override
	public void run(String... strings) {

		//Populating embedded database here
		logger.info("Inserting data into database. Count is {}.", userRepository.count());
		User shubham = new User("Venkat", 2000);
		User pankaj = new User("Singa", 29000);
		User lewis = new User("Arjun", 550);

		userRepository.save(shubham);
		userRepository.save(pankaj);
		userRepository.save(lewis);
		logger.info("Done. Data: {}.", userRepository.findAll());
	}
}
