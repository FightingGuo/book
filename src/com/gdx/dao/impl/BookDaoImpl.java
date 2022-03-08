package com.gdx.dao.impl;

import com.gdx.bean.Book;
import com.gdx.dao.BookDao;
import java.util.List;

/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/2/2 - 14:59
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql="insert into book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getimgPath());
    }

    @Override
    public int deleteBookId(Integer id) {
        String sql="delete from book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getimgPath(),book.getId());
    }

    @Override
    public Book queryBookId(Integer id) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from book";
        return queryForList(Book.class,sql);
    }

    @Override
    public int queryTotalCount() {
        return 0;
    }

    @Override
    public Integer queryPageTotalCount() {
        String sql="select count(*) from book";
        Number number = (Number) querySingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<Book> queryFofItems(int begin,int pageSize) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }


    @Override
    public Integer queryPageTotalCountByPrice(int min, int max) {
        String sql="select count(*) from book where price between ? and ?";
        Number number = (Number) querySingleValue(sql,min,max);
        return number.intValue();
    }

    @Override
    public List<Book> queryFofItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from book  where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }

}
