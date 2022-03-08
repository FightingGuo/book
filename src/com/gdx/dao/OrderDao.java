package com.gdx.dao;

import com.gdx.bean.Order;

import java.util.List;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/20 - 17:32
 */
public interface OrderDao {
    public int saveOrder(Order order); //���涩��  ����Ա�鿴
    public List<Order> queryOrder();  //��ѯȫ������
    public void changeOrderStatus(Integer status,String orderId);//�޸Ķ���״̬  status 0:��ʾ�ѷ���  1:��ʾδ���� 2:��ʾ��ǩ��
    public List<Order> queryOrdersByUserId(Integer userId);//�����û���Ų�ѯ������Ϣ
    public int queryTotalCount(); //��ҳ��
    public List<Order> queryFofItems(int begin, int pageSize);
    public Integer queryPageTotalCount();//���ܼ�¼��
    public Integer queryPageTotalCountByPrice(int min,int max);//���ܼ�¼��
    public List<Order> queryFofItemsByPrice(int begin,int pageSize,int min,int max);
}
