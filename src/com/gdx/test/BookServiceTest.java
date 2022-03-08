package com.gdx.test;

import com.gdx.bean.Book;
import com.gdx.bean.Page;
import com.gdx.service.impl.BookServiceImpl;
import org.junit.Test;


/**
 * @author π˘Íª≥ø
 * @version 1.0
 * 2022/2/2 - 17:29
 */
public class BookServiceTest {
    BookServiceImpl bookService=new BookServiceImpl();

    //≤‚ ‘∫ÕBookDaoTest≤‚ ‘“ª—˘
    @Test
    public void addBook() {

    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        bookService.page(1, Page.PAGE_SIZE);
    }
}