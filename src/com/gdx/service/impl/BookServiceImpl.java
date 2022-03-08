package com.gdx.service.impl;

import com.gdx.bean.Book;
import com.gdx.bean.Page;
import com.gdx.dao.BookDao;
import com.gdx.dao.impl.BaseDao;
import com.gdx.dao.impl.BookDaoImpl;
import com.gdx.service.BookService;

import java.util.List;

/**
 * @author ��껳�
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

        //����ÿҳ������
        page.setPageSize(pageSize);
        //���ܼ�¼��
        Integer pageTotalCount = bookDao.queryPageTotalCount();
        //�����ܼ�¼��
        page.setPageTotalCount(pageTotalCount);

        //����ҳ��
        Integer pageTotal=pageTotalCount / pageSize;
        if (pageTotalCount % pageSize >0){
            pageTotal++;
        }

        page.setPageTotal(pageTotal);

        //���õ�ǰҳ��
        page.setPageNo(pageNo);

        //��ǰҳ����
        int begin=(page.getPageNo()-1)*pageSize; //��ǰҳ-1  �� ÿҳ��
        List<Book> items = bookDao.queryFofItems(begin,pageSize);
        //���õ�ǰҳ����
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

        //����ÿҳ������
        page.setPageSize(pageSize);
        //���ڲ�ѯ��Χ�ڵ��ܼ�¼��
        Integer pageTotalCount = bookDao.queryPageTotalCountByPrice(min, max);

        //�����ܼ�¼��
        page.setPageTotalCount(pageTotalCount);

        //����ҳ��
        Integer pageTotal=pageTotalCount / pageSize;
        if (pageTotalCount % pageSize >0){
            pageTotal++;
        }

        page.setPageTotal(pageTotal);

        //���õ�ǰҳ��
        page.setPageNo(pageNo);

        //��ǰҳ����
        int begin=(page.getPageNo()-1)*pageSize; //��ǰҳ-1  �� ÿҳ��
        List<Book> items = bookDao.queryFofItemsByPrice(begin,pageSize,min,max);
        //���õ�ǰҳ����
        page.setItems(items);
        return page;
    }


}
