package com.gdx.test;

import com.gdx.bean.Book;
import com.gdx.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/2/2 - 15:36
 */
public class BookDaoTest {
    BookDaoImpl bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"¼ÆËã»úÍøÂç","Ð»Ï£ÈË",new BigDecimal(59.98),188,1999,"static/img/default.jpg"));

    }

    @Test
    public void deleteBookId() {
        bookDao.deleteBookId(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(null,"¼ÆËã»úÍøÂç","Ð»Ï£ÈË",new BigDecimal(59.98),188,1999,"static/img/default.jpg"));
    }

    @Test
    public void queryBookId() {
        System.out.println(bookDao.queryBookId(22));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }

    @Test
    public void queryPageTotalCount() {
        System.out.println(bookDao.queryPageTotalCount());
    }

    @Test
    public void  queryFofItems() {
        System.out.println(bookDao.queryFofItems(1,4));
    }
}