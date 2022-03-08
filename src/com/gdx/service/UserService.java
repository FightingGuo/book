package com.gdx.service;

import com.gdx.bean.User;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/1/18 - 14:12
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return 返回null登录失败，有返回值 登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
