package com.gdx.bean;

import java.math.BigDecimal;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/18 - 17:31
 */
public class CartItem {
    /**
     * ���ﳵ����Ʒ��
     */
    private Integer id;             //��Ʒ���
    private String name;            //��Ʒ����
    private Integer count;          //��Ʒ����
    private BigDecimal price;       //��Ʒ����
    private BigDecimal totalPrice;  //��Ʒ�ܼ۸�

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
