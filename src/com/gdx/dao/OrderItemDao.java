package com.gdx.dao;

import com.gdx.bean.Order;
import com.gdx.bean.OrderItem;
import com.gdx.bean.Page;

import java.util.List;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/20 - 17:38
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem); //保存订单的具体信息   客户查看
    public List<OrderItem> queryOrderByOrderId(String orderId); //根据用户订单号 返回查询订单明细
    public int queryTotalCount(); //总页码
    public List<OrderItem> queryFofItems(int begin, int pageSize);
    public Integer queryPageTotalCount();//求总记录数
    public Integer queryPageTotalCountByPrice(int min,int max);//求总记录数
    public List<OrderItem> queryFofItemsByPrice(int begin,int pageSize,int min,int max);
}
