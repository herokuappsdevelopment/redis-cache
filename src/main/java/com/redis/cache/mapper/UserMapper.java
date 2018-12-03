/**
 * 
 */
package com.redis.cache.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.redis.cache.model.User;

/**
 * @author VenkatS
 *
 */
@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);
	
	@Update("Update user set name= #{name},followers=#{followers} where id=#{id}")
	void updateUserByID(User user);
	
	@Delete("DELETE FROM user WHERE id = #{id}")
	void deleteById(Integer id);
	
	@Insert("insert into user(name,followers) values(#{name},#{followers})")
	@SelectKey(statement = "SELECT LAST_INSERT_ID();", 
	before = false, 
	keyProperty = "id", 
	resultType = Integer.class)
    void insertUser(User user);
	
	@Select("select * from user")
    List<User> findAll();

}
