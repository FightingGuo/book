package com.gdx.threadlocal;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/3/2 - 13:38
 */
public class ThreadLocalTest {
    public  static Map<String,Object> data=new ConcurrentHashMap<String,Object>();
    public  static ThreadLocal<Object> threadLocal=new ThreadLocal<Object>(); //ThreadLocal key����ǵ�ǰ�߳�
    /**
     * ThreadLocalֻ�ܹ���һ������
     * ÿ��ThreadLocal����ʵ��һ�㶨��Ϊstatic����
     */


    private static Random random=new Random();

    public static class Task implements Runnable{

        @Override
        public void run() {
            //��run�����У��������һ�������(�߳�Ҫ����������),Ȼ���Ե�ǰ�߳���Ϊkey���浽Map��
            int i=random.nextInt(1000); //����0-999�������
            String name=Thread.currentThread().getName();
            System.out.println("�߳�["+name+"]���ɵ������Ϊ:"+i);
//            data.put(name,i);

            threadLocal.set(i);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //��run��������ǰ���Ե�ǰ�߳�����ȡ�����ݲ���ӡ���鿴�Ƿ����ȡ������
//            Object o = data.get(name);
            Object o=threadLocal.get();

            System.out.println("���߳�["+name+"]�����ʱȡ������������"+o);

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <3; i++) {
            new Thread(new Task()).start();
        }
    }

}
