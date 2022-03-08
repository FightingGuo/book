package com.gdx.test;

import com.gdx.bean.Cart;
import com.gdx.bean.CartItem;
import com.gdx.bean.OrderItem;
import com.gdx.dao.OrderDao;
import com.gdx.dao.OrderItemDao;
import com.gdx.dao.impl.OrderDaoImpl;
import com.gdx.dao.impl.OrderItemDaoImpl;
import com.gdx.service.OrderService;
import com.gdx.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/21 - 11:12
 */
public class OrderServiceImplTest {
    OrderService orderService=new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(3,"狼道",1,new BigDecimal("999"),new BigDecimal("999")));
        cart.addItem(new CartItem(4,"鬼谷子",1,new BigDecimal("899"),new BigDecimal("899")));
        cart.addItem(new CartItem(5,"大学英语四",1,new BigDecimal("99"),new BigDecimal("99")));

        System.out.println("订单号是"+orderService.createOrder(cart,2));
    }

    @Test
    public void showAllOrders() {
        System.out.println(orderService.showAllOrders());
    }

    @Test
    public void sendOrder() {
    }

    @Test
    public void showOrderDetails() {
        System.out.println(orderService.showOrderDetails("16454143641751"));
    }

    @Test
    public void showMyOrders() {
        System.out.println(orderService.showMyOrders(1));
    }

    @Test
    public void receiverOrder() {

    }
}