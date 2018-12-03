package com.redis.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.cache.model.User;

/**
 * @author VenkatS
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
