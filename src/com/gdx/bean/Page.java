package com.gdx.bean;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/4 - 17:06
 */

import java.util.List;

/**
 * �ɷ�ҳ����ͼ��������ҳ�Ķ���ģ��Page��
 * @param <T> �Ǿ����ģ���javaBean����
 * pageNo           ��ǰҳ��
 * pageTotal        ��ҳ��
 * pageTotalCount   �ܼ�¼��   �ܼ�¼���ɸ��� select * from book ��ѯ
 * pageSize         ÿҳ��ʾ����
 * items            ��ǰҳ����
 */

public class Page<T> {
    public static final Integer PAGE_SIZE =4;
    private Integer pageNo;
    private Integer pageTotal;
    private Integer pageTotalCount;
    private Integer pageSize= PAGE_SIZE;
    private List<T> items;
    //��ҳ���������ַ
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
        //���ݱ߽����Ч���
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
