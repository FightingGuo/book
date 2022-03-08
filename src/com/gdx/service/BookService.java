package com.gdx.service;

import com.gdx.bean.Book;
import com.gdx.bean.Page;

import java.util.List;

/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/2/2 - 17:17
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBook(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Page page(int pageNo,int pageSize);
    public List<Book> queryFofItemsByPrice(int begin, int pageSize, int min, int max);
    public Integer queryPageTotalCountByPrice(int min, int max);
    Page pageByPrice(int pageNo, int pageSize, int min, int max);
}
