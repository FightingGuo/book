package com.gdx.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/18 - 16:56
 */
public class Cart {
    /**
     * ���ﳵ����
     */
    // private Integer totalCount; ��Ʒ������
    // private BigDecimal totalPrice; ����Ʒ�۸�
    /**
     * keyʱ��Ʒ���
     * value��ʱ��Ʒ��Ϣ
     */
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();       //���ﳵ�����Ʒ��Ϣ

    /**
     * �����Ʒ��
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
    //�Ȳ鿴���ﳵ���Ƿ��Ѿ���ӣ�����Ѿ���ӣ��������ۼӣ��ܽ����£����û����ӣ�ֱ�Ӽӵ�������
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            //ȡ��ԭ��������+1
            item.setCount(item.getCount()+1);
            //ȡ��ԭ���ĵ��� * ��Ʒ������  multiply��ʾ�˷�
            item.setTotalPrice(item.getTotalPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * ɾ����Ʒ��
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * ��չ��ﳵ
     */
    public void clear(){
        items.clear();
    }

    /**
     * �޸���Ʒ����
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
    //�Ȳ鿴���ﳵ���Ƿ��д���Ʒ��������޸���Ʒ�����������ܽ��
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getTotalPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Cart() {
    }

    public Cart(BigDecimal totalPrice, Map<Integer,CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice; ͬ����һ������ֱ������
//    }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) { ����ֱ�Ӷ���Ʒ������ֵ
//        this.totalCount = totalCount;  ��Ʒ����ʱͨ��cart�����count�����ۼӵõ�
//    }

    public BigDecimal getTotalPrice() {
      BigDecimal totalPrice=new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }


        return totalPrice;
    }


    public Map<Integer,CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
