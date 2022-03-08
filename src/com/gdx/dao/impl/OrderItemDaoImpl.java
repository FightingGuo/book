package com.gdx.dao.impl;

import com.gdx.bean.Order;
import com.gdx.bean.OrderItem;
import com.gdx.bean.Page;
import com.gdx.dao.OrderItemDao;

import java.util.List;

/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/2/20 - 18:04
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderByOrderId(String orderId) {
        String sql="select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }

    @Override
    public int queryTotalCount() {
        return 0;
    }

    @Override
    public List<OrderItem> queryFofItems(int begin, int pageSize) {
        String sql="select `id`,`name`,`count`,`price`,`totalPrice`,`user_id` userId from t_order_item limit ?,?";
        return queryForList(OrderItem.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCount() {
        String sql="select count(*) from t_order_item";
        Number number = (Number) querySingleValue(sql);
        return number.intValue();
    }

    @Override
    public Integer queryPageTotalCountByPrice(int min, int max) {
        String sql="select count(*) from t_order_item where price between ? and ?";
        Number number = (Number) querySingleValue(sql,min,max);
        return number.intValue();
    }

    @Override
    public List<OrderItem> queryFofItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select `id`,`name`,`count`,`price`,`totalPrice`,`user_id` userId from t_order_item  where price between ? and ? order by price limit ?,?";
        return queryForList(OrderItem.class,sql,min,max,begin,pageSize);
    }

}
