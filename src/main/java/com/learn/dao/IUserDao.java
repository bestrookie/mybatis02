package com.learn.dao;

import com.learn.domian.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


public interface IUserDao {
    @Select("select * from user")
    @Results(id = "userMapper",value = {
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(property = "accounts",column = "id",many = @Many(select = "com.learn.dao.IAccountDao.findAccountByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();
    @Insert(" insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday})")
    @ResultMap("userMapper")
    void saveUser(User user);
    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address} where id =#{id}")
    @ResultMap("userMapper")
    void updateUser(User user);
    @Delete("delete from user where id =#{id}")
    @ResultMap("userMapper")
    void deleteUser(Integer userId);
    @Select("select * from user where id =#{id}")
    @ResultMap("userMapper")
    User findById(Integer userId);
    @Select("select * from user where username like #{username}")
    @ResultMap("userMapper")
    List<User> findUserByName(String userName);
}
