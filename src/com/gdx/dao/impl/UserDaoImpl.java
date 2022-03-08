package com.gdx.dao.impl;

import com.gdx.bean.User;
import com.gdx.dao.UserDao;


/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/1/17 - 21:01
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql="select `id`,`username`,`password`,`email` from users where `username`=?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username,String password) {
        String sql="select `id`,`username`,`password`,`email` from users where username=? and password=?";
        return queryForOne(User.class, sql, username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into users(`username`,`password`,`email`)values(?,?,?)";
         return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
