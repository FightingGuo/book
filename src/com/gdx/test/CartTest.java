package com.gdx.test;

import com.gdx.bean.Cart;
import com.gdx.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/18 - 18:07
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(3,"狼道",1,new BigDecimal("999"),new BigDecimal("999")));
        cart.addItem(new CartItem(4,"鬼谷子",1,new BigDecimal("899"),new BigDecimal("899")));
        cart.addItem(new CartItem(5,"大学英语四",1,new BigDecimal("99"),new BigDecimal("99")));

        cart.deleteItem(1);

        cart.clear();

        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal("1999"),new BigDecimal("1999")));

        cart.updateCount(1,10);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void updateCount() {
    }
}