package com.gdx.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/1 - 18:11
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        //�����Ӧ����������
        resp.setContentType("text/html; charset=UTF-8");

        try {
            //��ȡactionҵ������ַ�������ȡ��Ӧ��ҵ�񷽷��������
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e); //���쳣�׸�filter������
        }

    }
}
