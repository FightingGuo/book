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
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/2 - 17:36
 */
public class BookServlet extends BaseServlet{
   private BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //1.获取请求的参数==封装成为book对象
        Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book());

        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3.返回图书列表    /manager/bookServlet?action=list 分页后返回page

//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);  如果用请求转发 用户没按一次F5就会发起浏览器记录的最后一次请求
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
        //重定向的 / 表示到端口号
    }

    /**
     * 回显用户此时修改的书 的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //从客户端接收修改数据的id
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //通过id查询到这本书在数据库中的数据
        Book book = bookService.queryBookById(id);
        //把数据保存到request域中
        req.setAttribute("book",book);
        //请求转发回修改页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取修改数据的参数并封装成一个bean对象
        Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //调用Dao修改数据库
        bookService.updateBook(book);
        //请求重定向会图书管理界面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数id，图书编号
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.deleteBook()删除图书
        bookService.deleteBook(id);
        //3.重定向回图书管理列表
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询数据库中所有图书
        List<Book> books = bookService.queryBooks();

        //把数据保存在request域中
        req.setAttribute("books",books);

        //请求转发到pages/manager/manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);


    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo和 pageSize
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo,pageSize)  返回page对象
        Page page = bookService.page(pageNo,pageSize);
        //设置后台url地址
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到 /pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
