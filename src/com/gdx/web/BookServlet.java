package com.gdx.web;

import com.gdx.bean.Book;
import com.gdx.bean.Page;
import com.gdx.service.BookService;
import com.gdx.service.impl.BookServiceImpl;
import com.gdx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/2 - 17:36
 */
public class BookServlet extends BaseServlet{
   private BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //1.��ȡ����Ĳ���==��װ��Ϊbook����
        Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book());

        //2.����BookService.addBook()����ͼ��
        bookService.addBook(book);
        //3.����ͼ���б�    /manager/bookServlet?action=list ��ҳ�󷵻�page

//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);  ���������ת�� �û�û��һ��F5�ͻᷢ���������¼�����һ������
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
        //�ض���� / ��ʾ���˿ں�
    }

    /**
     * �����û���ʱ�޸ĵ��� ������
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //�ӿͻ��˽����޸����ݵ�id
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //ͨ��id��ѯ���Ȿ�������ݿ��е�����
        Book book = bookService.queryBookById(id);
        //�����ݱ��浽request����
        req.setAttribute("book",book);
        //����ת�����޸�ҳ��
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //��ȡ�޸����ݵĲ�������װ��һ��bean����
        Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //����Dao�޸����ݿ�
        bookService.updateBook(book);
        //�����ض����ͼ��������
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.��ȡ�������id��ͼ����
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //2.����bookService.deleteBook()ɾ��ͼ��
        bookService.deleteBook(id);
        //3.�ض����ͼ������б�
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ѯ���ݿ�������ͼ��
        List<Book> books = bookService.queryBooks();

        //�����ݱ�����request����
        req.setAttribute("books",books);

        //����ת����pages/manager/manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);


    }

    /**
     * �����ҳ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.��ȡ����Ĳ��� pageNo�� pageSize
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        //2.����bookService.page(pageNo,pageSize)  ����page����
        Page page = bookService.page(pageNo,pageSize);
        //���ú�̨url��ַ
        page.setUrl("manager/bookServlet?action=page");
        //3.����page����Request����
        req.setAttribute("page",page);
        //4.����ת���� /pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
