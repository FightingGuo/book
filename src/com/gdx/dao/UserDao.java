package com.gdx.dao;

import com.gdx.bean.User;
import org.junit.Test;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/1/17 - 20:45
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回null，说明没有这个用户，反之..
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户的用户名和密码查询是否有该用户
     * @param username
     * @return 如果有null说明没有该用户 反之..
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败，其他表示影响的行数
     */
    public int saveUser(User user);


}
