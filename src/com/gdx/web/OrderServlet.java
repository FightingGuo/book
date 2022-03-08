package com.gdx.web;

import com.gdx.bean.*;
import com.gdx.service.OrderService;
import com.gdx.service.impl.OrderServiceImpl;
import com.gdx.utils.JdbcUtils;
import com.gdx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/21 - 12:25
 */
public class OrderServlet extends BaseServlet{

    OrderService orderService=new OrderServiceImpl();

    /**
     * ���ɶ���
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��Session����ȡ��cart���ﳵ����
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //��Session����ȡ���û�id ��¼��ʱ�򱣴��һ��user������Session�� ͨ��user����ȡ��id
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            //���user����Ϊ��˵����û�е�¼
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=user.getId();
        //����Service�������ݿ��õ�orderId

        String orderId = orderService.createOrder(cart,userId);

        //��orderId���浽Session����
        req.getSession().setAttribute("orderId",orderId);

        //�ض���ؽ���ɹ�ҳ��
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }


    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡrequest���е�orderId
        String orderId = req.getParameter("orderId");
        //����service�ķ����鿴������ϸ��Ϣ
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);
        //�Ѷ�����Ϣ����Session����
        req.getSession().setAttribute("orderItems",orderItems);
        //4.����ת���� /pages/order/orderDetails.jspҳ��
        req.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(req,resp);


    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * ���˷�ҳ���������ʱ�ò���
         */
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            //���user����Ϊ��˵����û�е�¼
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=user.getId();
        List<Order> orders = orderService.showMyOrders(userId);
        //�Ѷ������ݱ��浽Session����
        req.getSession().setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }

    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> allOrders = orderService.showAllOrders();

        //�����ж�����Ϣ�浽Session����
        req.getSession().setAttribute("allOrders",allOrders);

        //4.����ת���� /pages/manager/order_manager.jspҳ��
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);


    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(0,orderId);
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showAllOrders");
    }

    /**
     * �鿴�ҵ����ж���
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            //���user����Ϊ��˵����û�е�¼
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=user.getId();
        List<Order> orders = orderService.showMyOrders(userId);
        //�Ѷ������ݱ��浽Session����
        req.getSession().setAttribute("orders",orders);

        //4.����ת���� /pages/order/order.jspҳ��
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);


    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ҳ������ȡ
    }
}
