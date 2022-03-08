package com.gdx.bean;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/4 - 17:06
 */

import java.util.List;

/**
 * 由分页的试图分析出分页的对象模型Page类
 * @param <T> 是具体的模块的javaBean对象
 * pageNo           当前页码
 * pageTotal        总页码
 * pageTotalCount   总记录数   总记录数可根据 select * from book 查询
 * pageSize         每页显示数量
 * items            当前页数据
 */

public class Page<T> {
    public static final Integer PAGE_SIZE =4;
    private Integer pageNo;
    private Integer pageTotal;
    private Integer pageTotalCount;
    private Integer pageSize= PAGE_SIZE;
    private List<T> items;
    //分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageTotalCount, Integer pageSize, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.pageSize = pageSize;
        this.items=items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //数据边界的有效检查
        pageNo=Math.max(1,Math.min(pageNo,pageTotal));
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }


    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
