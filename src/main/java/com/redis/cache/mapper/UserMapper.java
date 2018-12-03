/**
 * 
 */
package com.redis.cache.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.redis.cache.model.User;

/**
 * @author VenkatS
 *
 */
@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Long id);
	
	@Update("Update user set name= #{name},followers=#{followers} where id=#{id}")
	void updateUserByID(User usr);
	
	@Delete("DELETE FROM user WHERE id = #{id}")
	void deleteById(Long id);

}
