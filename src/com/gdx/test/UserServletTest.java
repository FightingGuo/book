package com.gdx.test;


import java.lang.reflect.Method;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/1 - 11:11
 */
public class UserServletTest {

    public void login(){
        System.out.println("����login()����");
    }
    public void regist(){
        System.out.println("����regist()����");
    }
    public void upUsername(){
        System.out.println("����upUsername()����");
    }
    public void upPhone(){
        System.out.println("����upPhone()����");
    }

    public static void main(String[] args) {
        String action="login";
        try {
            Method declaredMethods = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(declaredMethods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
