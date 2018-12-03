/**
 * 
 */
package com.redis.cache.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
    User findById(Long id);
	
	@Update("Update user set name= #{name},followers=#{followers} where id=#{id}")
	void updateUserByID(User user);
	
	@Delete("DELETE FROM user WHERE id = #{id}")
	void deleteById(Long id);
	
	@Insert("insert into user(name,followers) values(#{name},#{followers})")
    @SelectKey(statement="call identity()", keyProperty="id",
    before=false, resultType=Integer.class)
    void insertUser(User user);

}
