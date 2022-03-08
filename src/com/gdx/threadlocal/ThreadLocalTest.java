package com.gdx.threadlocal;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/3/2 - 13:38
 */
public class ThreadLocalTest {
    public  static Map<String,Object> data=new ConcurrentHashMap<String,Object>();
    public  static ThreadLocal<Object> threadLocal=new ThreadLocal<Object>(); //ThreadLocal key存的是当前线程
    /**
     * ThreadLocal只能关联一个数据
     * 每个ThreadLocal对象实例一般定义为static类型
     */


    private static Random random=new Random();

    public static class Task implements Runnable{

        @Override
        public void run() {
            //在run方法中，随机生成一个随机数(线程要关联的数据),然后以当前线程名为key保存到Map中
            int i=random.nextInt(1000); //生成0-999的随机数
            String name=Thread.currentThread().getName();
            System.out.println("线程["+name+"]生成的随机数为:"+i);
//            data.put(name,i);

            threadLocal.set(i);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //在run方法结束前，以当前线程名获取出数据并打印。查看是否可以取出操作
//            Object o = data.get(name);
            Object o=threadLocal.get();

            System.out.println("在线程["+name+"]快结束时取出关联数据是"+o);

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <3; i++) {
            new Thread(new Task()).start();
        }
    }

}
