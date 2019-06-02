package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @description:
 * @author: zhangyi
 * @since: 2019-04-14 23:56
 */
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<User> getAll();

    @Select("SELECT * FROM users WHERE uid = #{uid}")
    User getOneByUid(Long uid);

    @Select("SELECT * FROM users WHERE user_name = #{name}")
    User getOneByName(String name);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User getOneByEmail(String email);

    @Insert("INSERT INTO users(uid,user_name,pass_word,nick_name,email,wechat,jurisdiction) VALUES(#{uid}, #{userName}, #{passWord}, #{nickName},#{email},#{wechat},#{jurisdiction})")
    void insert(User user);

    @Update("UPDATE users SET nick_name=#{nickName},email=#{email} WHERE uid =#{uid}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}
