package com.gdx.dao;

import com.gdx.bean.Order;
import com.gdx.bean.OrderItem;
import com.gdx.bean.Page;

import java.util.List;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/20 - 17:38
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem); //���涩���ľ�����Ϣ   �ͻ��鿴
    public List<OrderItem> queryOrderByOrderId(String orderId); //�����û������� ���ز�ѯ������ϸ
    public int queryTotalCount(); //��ҳ��
    public List<OrderItem> queryFofItems(int begin, int pageSize);
    public Integer queryPageTotalCount();//���ܼ�¼��
    public Integer queryPageTotalCountByPrice(int min,int max);//���ܼ�¼��
    public List<OrderItem> queryFofItemsByPrice(int begin,int pageSize,int min,int max);
}
