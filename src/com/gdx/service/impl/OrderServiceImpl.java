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
 * @author ��껳�
 * @version 1.0
 * 2022/2/21 - 10:04
 */
public class OrderServiceImpl extends BaseDao implements OrderService {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private OrderDao orderDao=new OrderDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //������===Ψһ��
        String orderId=System.currentTimeMillis()+""+userId;
        //������������
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //���涩��
        orderDao.saveOrder(order);

        int i=10/0;

        /**
         * ���涩������ﳵcart�е�ÿһ��cartItem ����һ��������  ��cartItemתΪorderItem
         */

        //�������ﳵ��ÿһ����Ʒ��ת��Ϊ��������浽���ݿ���
        for (Map.Entry<Integer, CartItem>entry :cart.getItems().entrySet()){
            //��ȡÿһ�����ﳵ�е���Ʒ�� ȡ��ÿһ��cartItem
            CartItem cartItem=entry.getValue();
            //ת��Ϊÿһ��������
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //���涩������ݿ�
            orderItemDao.saveOrderItem(orderItem);

            /**
             * ���¿�������
             * ��Ϊ���û������ˣ�������ҳ����������Ϳ��Ҳ��Ҫ���޸�
             */

            //ͨ���鵽�û��������
            Book book = bookDao.queryBookId(cartItem.getId());
            //�޸����ݿ��е������Ϳ��
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            //���޸ĺ�Ĳ�������
            bookDao.updateBook(book);
        }
        //����Ǯ����չ��ﳵ
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        //�õ����ж���
       return orderDao.queryOrder();
    }

    /**
     * 0��ʾ�ѷ�����1��ʾδ���� 2��ʾ��ǩ��
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
