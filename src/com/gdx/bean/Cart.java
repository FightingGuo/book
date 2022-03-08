package com.gdx.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/18 - 16:56
 */
public class Cart {
    /**
     * 购物车对象
     */
    // private Integer totalCount; 商品总数量
    // private BigDecimal totalPrice; 总商品价格
    /**
     * key时商品编号
     * value，时商品信息
     */
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();       //购物车里的商品信息

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
    //先查看购物车中是否已经添加，如果已经添加，则数量累加，总金额更新，如果没有添加，直接加到集合中
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            //取出原来的数量+1
            item.setCount(item.getCount()+1);
            //取出原来的单价 * 商品总数量  multiply表示乘法
            item.setTotalPrice(item.getTotalPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
    //先查看购物车中是否有此商品。如果有修改商品数量，更新总金额
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
//        this.totalPrice = totalPrice; 同数量一样不能直接设置
//    }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) { 不能直接对商品总数赋值
//        this.totalCount = totalCount;  商品总数时通过cart对象的count属性累加得到
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
