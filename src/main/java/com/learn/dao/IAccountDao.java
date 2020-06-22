package com.learn.dao;

import com.learn.domian.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author : bestrookie
 * @date : 22:09 2020/6/22
 */
public interface IAccountDao {
@Select("select * from account")
@Results(id = "accountMap",value = {
        @Result(id = true,column = "id",property = "id"),
        @Result(column = "uid",property = "uid"),
        @Result(column = "money",property = "money"),
        @Result(property = "user",column = "uid",one = @One( select="com.learn.dao.IUserDao.findById",fetchType = FetchType.EAGER))
})
    List<Account> finAll();
@Select("select * from account where uid =#{userId}")
    List<Account> findAccountByUid(Integer userId);
}
