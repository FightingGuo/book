package com.gdx.dao;

import com.gdx.bean.Order;

import java.util.List;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/20 - 17:32
 */
public interface OrderDao {
    public int saveOrder(Order order); //保存订单  管理员查看
    public List<Order> queryOrder();  //查询全部订单
    public void changeOrderStatus(Integer status,String orderId);//修改订单状态  status 0:表示已发货  1:表示未发货 2:表示已签收
    public List<Order> queryOrdersByUserId(Integer userId);//根据用户编号查询订单信息
    public int queryTotalCount(); //总页码
    public List<Order> queryFofItems(int begin, int pageSize);
    public Integer queryPageTotalCount();//求总记录数
    public Integer queryPageTotalCountByPrice(int min,int max);//求总记录数
    public List<Order> queryFofItemsByPrice(int begin,int pageSize,int min,int max);
}
