package com.gdx.service.impl;

import com.gdx.bean.User;
import com.gdx.dao.UserDao;
import com.gdx.dao.impl.UserDaoImpl;
import com.gdx.service.UserService;

/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/1/18 - 14:21
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}
