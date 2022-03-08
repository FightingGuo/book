package com.gdx.test;

import com.gdx.bean.User;
import com.gdx.service.UserService;
import com.gdx.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/1/18 - 14:44
 */
public class UserServiceTest {

    UserService userService=new UserServiceImpl();

    @Test
    public void registerUser() {
    userService.registerUser(new User(null,"许嵩","xusong123","xusong@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"admin","admin",null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("dsad")){
            System.out.println("用户名存在");
        }else {
            System.out.println("用户名不存在");
        }
    }
}