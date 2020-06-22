package com.learn.dao;


import com.learn.domian.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author : bestrookie
 * @date : 20:18 2020/6/22
 */
public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init(){
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void destroy(){
        sqlSession.commit();
        sqlSession.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("mybatis_01");
        user.setUserAddress("山东省");
        userDao.saveUser(user);

    }
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(49);
        user.setUserName("mybatis_02");
        user.setUserAddress("山东");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        userDao.updateUser(user);
    }
    @Test
    public void testDelete(){
        userDao.deleteUser(49);
    }
    @Test
    public void testFindById(){
        User user = userDao.findById(48);
        System.out.println(user);
    }
    @Test
    public  void testFindByName(){
        List<User> users = userDao.findUserByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("----每个用户的信息");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
}
