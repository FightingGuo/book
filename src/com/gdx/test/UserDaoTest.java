package com.gdx.test;

import com.gdx.bean.User;
import com.gdx.dao.UserDao;
import com.gdx.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/1/17 - 21:26
 */
public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if(userDao.queryUserByUsername("admin")!=null){
            System.out.println("用户已被注册,请重新试试\n");
        }else{
            System.out.println("用户名可用！");
        }

    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin")!=null){
            System.out.println("登录成功！");
        }else{
            System.out.println("用户名或密码错误");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"gg","admin123","123456@qq.com")));
    }
}