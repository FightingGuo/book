package com.gdx.dao;

import com.gdx.bean.User;
import org.junit.Test;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/1/17 - 20:45
 */
public interface UserDao {

    /**
     * �����û�����ѯ�û���Ϣ
     * @param username
     * @return �������null��˵��û������û�����֮..
     */
    public User queryUserByUsername(String username);

    /**
     * �����û����û����������ѯ�Ƿ��и��û�
     * @param username
     * @return �����null˵��û�и��û� ��֮..
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * �����û���Ϣ
     * @param user
     * @return ����-1��ʾ����ʧ�ܣ�������ʾӰ�������
     */
    public int saveUser(User user);


}
