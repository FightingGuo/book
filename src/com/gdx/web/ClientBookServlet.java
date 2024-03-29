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

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/7 - 14:16
 */
public class ClientBookServlet extends BaseServlet{
   private BookService bookService=new BookServiceImpl();
    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo和 pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo,pageSize)  返回page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        //设置前台url地址
        page.setUrl("client/bookServlet?action=page");
        //3.保存page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到 /pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 通过价格区间查找符合条件的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo和 pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        int min=WebUtils.parseInt(req.getParameter("min"),0);
        int max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //2.调用bookService.pageByPrice(pageNo,pageSize)  返回page对象
            Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小价格的参数，追加到分页条地址参数中
        if (req.getParameter("min")!=null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        //如果有最大价格的参数，追加到分页条地址参数中
        if (req.getParameter("max")!=null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        //设置前台url地址
        page.setUrl(sb.toString());
        //3.保存page对象到Request域中
        req.setAttribute("page",page);

        //4.请求转发到 /pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }



}
