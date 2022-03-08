package com.gdx.test;


import java.lang.reflect.Method;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/1 - 11:11
 */
public class UserServletTest {

    public void login(){
        System.out.println("这是login()方法");
    }
    public void regist(){
        System.out.println("这是regist()方法");
    }
    public void upUsername(){
        System.out.println("这是upUsername()方法");
    }
    public void upPhone(){
        System.out.println("这是upPhone()方法");
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
