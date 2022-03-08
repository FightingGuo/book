package com.gdx.service;

import com.gdx.bean.Cart;
import com.gdx.bean.Order;
import com.gdx.bean.OrderItem;
import com.gdx.bean.Page;

import java.util.List;

/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/2/20 - 15:57
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

    public List<Order> showAllOrders();

    public void sendOrder(Integer status,String orderId);

    public List<OrderItem> showOrderDetails(String orderId);

    public List<Order> showMyOrders(Integer userId);

    public void receiverOrder(Integer status,String orderId);

}