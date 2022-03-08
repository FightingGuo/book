package com.gdx.dao;

import com.gdx.bean.Book;

import java.util.List;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/2 - 14:51
 */
public interface BookDao {
    public int addBook(Book book);
    public int deleteBookId(Integer id);
    public int updateBook(Book book);
    public Book queryBookId(Integer id);
    public List<Book> queryBooks();
    public int queryTotalCount(); //��ҳ��
    public List<Book> queryFofItems(int begin,int pageSize);
    public Integer queryPageTotalCount();//���ܼ�¼��
    public Integer queryPageTotalCountByPrice(int min,int max);//���ܼ�¼��
    public List<Book> queryFofItemsByPrice(int begin,int pageSize,int min,int max);
}
