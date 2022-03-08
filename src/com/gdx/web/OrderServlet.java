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
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/21 - 12:25
 */
public class OrderServlet extends BaseServlet{

    OrderService orderService=new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从Session域中取出cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //从Session域中取出用户id 登录的时候保存过一个user对象在Session中 通过user对象取出id
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            //如果user对象为空说明还没有登录
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=user.getId();
        //调用Service操作数据库拿到orderId

        String orderId = orderService.createOrder(cart,userId);

        //把orderId保存到Session域中
        req.getSession().setAttribute("orderId",orderId);

        //重定向回结算成功页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }


    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取request域中的orderId
        String orderId = req.getParameter("orderId");
        //调用service的方法查看订单详细信息
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);
        //把订单信息存入Session域中
        req.getSession().setAttribute("orderItems",orderItems);
        //4.请求转发到 /pages/order/orderDetails.jsp页面
        req.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(req,resp);


    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 做了分页这个方法暂时用不上
         */
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            //如果user对象为空说明还没有登录
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=user.getId();
        List<Order> orders = orderService.showMyOrders(userId);
        //把订单数据保存到Session域中
        req.getSession().setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }

    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> allOrders = orderService.showAllOrders();

        //把所有订单信息存到Session域中
        req.getSession().setAttribute("allOrders",allOrders);

        //4.请求转发到 /pages/manager/order_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);


    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(0,orderId);
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showAllOrders");
    }

    /**
     * 查看我的所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            //如果user对象为空说明还没有登录
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=user.getId();
        List<Order> orders = orderService.showMyOrders(userId);
        //把订单数据保存到Session域中
        req.getSession().setAttribute("orders",orders);

        //4.请求转发到 /pages/order/order.jsp页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);


    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //分页方法抽取
    }
}
