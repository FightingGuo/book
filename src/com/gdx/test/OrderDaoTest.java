package com.gdx.test;

import com.gdx.bean.Order;
import com.gdx.dao.OrderDao;
import com.gdx.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/2/20 - 18:17
 */
public class OrderDaoTest {
    OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void saveOrder() {

        orderDao.saveOrder(new Order("123442",new Date(),new BigDecimal(200),0,1));
        orderDao.saveOrder(new Order("123552",new Date(),new BigDecimal(100),0,2));
        orderDao.saveOrder(new Order("123662",new Date(),new BigDecimal(300),0,5));

    }

    @Test
    public void queryOrder() {
        System.out.println(orderDao.queryOrder());
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus(1,"123552");
    }

    @Test
    public void queryOrdersByUserId() {
        System.out.println(orderDao.queryOrdersByUserId(1));
    }
}