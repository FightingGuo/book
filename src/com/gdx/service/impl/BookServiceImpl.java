package com.gdx.service.impl;

import com.gdx.bean.Book;
import com.gdx.bean.Page;
import com.gdx.dao.BookDao;
import com.gdx.dao.impl.BaseDao;
import com.gdx.dao.impl.BookDaoImpl;
import com.gdx.service.BookService;

import java.util.List;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/2 - 17:24
 */
public class BookServiceImpl  implements BookService {

    private BookDao bookDao =new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBookId(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
       return bookDao.queryBookId(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page page(int pageNo, int pageSize) {
        Page<Book> page=new Page<Book>();

        //设置每页的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal=pageTotalCount / pageSize;
        if (pageTotalCount % pageSize >0){
            pageTotal++;
        }

        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据
        int begin=(page.getPageNo()-1)*pageSize; //当前页-1  乘 每页数
        List<Book> items = bookDao.queryFofItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);


        return page;
    }

    @Override
    public List<Book> queryFofItemsByPrice(int begin, int pageSize, int min, int max) {

         return bookDao.queryFofItemsByPrice(begin, pageSize, min, max);

    }

    @Override
    public Integer queryPageTotalCountByPrice(int min, int max) {
        return bookDao.queryPageTotalCountByPrice(min, max);
    }

    @Override
    public Page pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page=new Page<Book>();

        //设置每页的数量
        page.setPageSize(pageSize);
        //求在查询范围内的总记录数
        Integer pageTotalCount = bookDao.queryPageTotalCountByPrice(min, max);

        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal=pageTotalCount / pageSize;
        if (pageTotalCount % pageSize >0){
            pageTotal++;
        }

        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据
        int begin=(page.getPageNo()-1)*pageSize; //当前页-1  乘 每页数
        List<Book> items = bookDao.queryFofItemsByPrice(begin,pageSize,min,max);
        //设置当前页数据
        page.setItems(items);
        return page;
    }


}
