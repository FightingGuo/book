package com.gdx.test;

import com.gdx.bean.User;
import com.gdx.dao.UserDao;
import com.gdx.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/1/17 - 21:26
 */
public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if(userDao.queryUserByUsername("admin")!=null){
            System.out.println("�û��ѱ�ע��,����������\n");
        }else{
            System.out.println("�û������ã�");
        }

    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin")!=null){
            System.out.println("��¼�ɹ���");
        }else{
            System.out.println("�û������������");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"gg","admin123","123456@qq.com")));
    }
}