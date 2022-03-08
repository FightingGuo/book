package com.gdx.service.impl;

import com.gdx.bean.*;
import com.gdx.dao.BookDao;
import com.gdx.dao.OrderDao;
import com.gdx.dao.OrderItemDao;
import com.gdx.dao.impl.BaseDao;
import com.gdx.dao.impl.BookDaoImpl;
import com.gdx.dao.impl.OrderDaoImpl;
import com.gdx.dao.impl.OrderItemDaoImpl;
import com.gdx.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/21 - 10:04
 */
public class OrderServiceImpl extends BaseDao implements OrderService {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private OrderDao orderDao=new OrderDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号===唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        //创建订单对象
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

        int i=10/0;

        /**
         * 保存订单项，购物车cart中的每一个cartItem 就是一个订单项  把cartItem转为orderItem
         */

        //遍历购物车中每一个商品项转化为订单项，保存到数据库中
        for (Map.Entry<Integer, CartItem>entry :cart.getItems().entrySet()){
            //获取每一个购物车中的商品项 取出每一个cartItem
            CartItem cartItem=entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            /**
             * 更新库存和销量
             * 因为有用户买书了，所以主页上书的销量和库存也需要做修改
             */

            //通过查到用户购买的书
            Book book = bookDao.queryBookId(cartItem.getId());
            //修改数据库中的销量和库存
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            //把修改后的操作保存
            bookDao.updateBook(book);
        }
        //付完钱后清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        //得到所有订单
       return orderDao.queryOrder();
    }

    /**
     * 0表示已发货，1表示未发货 2表示已签收
     * @param status
     * @param orderId
     */
    @Override
    public void  sendOrder(Integer status, String orderId) {
        orderDao.changeOrderStatus(0,orderId);
    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        return orderItemDao.queryOrderByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public void receiverOrder(Integer status,String orderId) {
        orderDao.changeOrderStatus(2,orderId);
    }

}
