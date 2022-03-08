package com.gdx.test;

import com.gdx.bean.OrderItem;
import com.gdx.dao.OrderDao;
import com.gdx.dao.OrderItemDao;
import com.gdx.dao.impl.OrderDaoImpl;
import com.gdx.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/20 - 19:40
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal("156"),new BigDecimal("156"),"123662"));
        orderItemDao.saveOrderItem(new OrderItem(null,"计算机网络",1,new BigDecimal("99"),new BigDecimal("99"),"123442"));
        orderItemDao.saveOrderItem(new OrderItem(null,"软件工程",1,new BigDecimal("192"),new BigDecimal("192"),"123552"));
    }

    @Test
    public void queryOrderByOrderId() {
        System.out.println(orderItemDao.queryOrderByOrderId("123552"));
    }
}