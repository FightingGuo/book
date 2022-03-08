package com.gdx.service;

import com.gdx.bean.User;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/1/18 - 14:12
 */
public interface UserService {

    /**
     * ע���û�
     * @param user
     */
    public void registerUser(User user);

    /**
     * ��¼
     * @param user
     * @return ����null��¼ʧ�ܣ��з���ֵ ��¼�ɹ�
     */
    public User login(User user);

    /**
     * ����û����Ƿ����
     * @param username
     * @return ����true��ʾ�û����Ѵ��ڣ�����false��ʾ�û�������
     */
    public boolean existsUsername(String username);
}
