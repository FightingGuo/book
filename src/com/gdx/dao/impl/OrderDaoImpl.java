package com.gdx.dao.impl;

import com.gdx.bean.Book;
import com.gdx.bean.Order;
import com.gdx.dao.OrderDao;

import java.util.List;

/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/2/20 - 17:44
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
       return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrder() {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public void changeOrderStatus(Integer status,String orderId) {
        String sql="update t_order set `status`=? where order_id=?";
        update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public int queryTotalCount() {
        return 0;
    }

    @Override
    public List<Order> queryFofItems(int begin, int pageSize) {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order limit ?,?";
        return queryForList(Order.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCount() {
        String sql="select count(*) from t_order";
        Number number = (Number) querySingleValue(sql);
        return number.intValue();
    }

    @Override
    public Integer queryPageTotalCountByPrice(int min, int max) {
        String sql="select count(*) from t_order where price between ? and ?";
        Number number = (Number) querySingleValue(sql,min,max);
        return number.intValue();
    }

    @Override
    public List<Order> queryFofItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order  where price between ? and ? order by price limit ?,?";
        return queryForList(Order.class,sql,min,max,begin,pageSize);
    }


}
