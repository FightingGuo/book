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
 * @author ��껳�
 * @version 1.0
 * 2022/2/7 - 14:16
 */
public class ClientBookServlet extends BaseServlet{
   private BookService bookService=new BookServiceImpl();
    /**
     * �����ҳ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.��ȡ����Ĳ��� pageNo�� pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        //2.����bookService.page(pageNo,pageSize)  ����page����
        Page<Book> page = bookService.page(pageNo,pageSize);
        //����ǰ̨url��ַ
        page.setUrl("client/bookServlet?action=page");
        //3.����page����Request����
        req.setAttribute("page",page);
        //4.����ת���� /pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * ͨ���۸�������ҷ�������������
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.��ȡ����Ĳ��� pageNo�� pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        int min=WebUtils.parseInt(req.getParameter("min"),0);
        int max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //2.����bookService.pageByPrice(pageNo,pageSize)  ����page����
            Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");
        //�������С�۸�Ĳ�����׷�ӵ���ҳ����ַ������
        if (req.getParameter("min")!=null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        //��������۸�Ĳ�����׷�ӵ���ҳ����ַ������
        if (req.getParameter("max")!=null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        //����ǰ̨url��ַ
        page.setUrl(sb.toString());
        //3.����page����Request����
        req.setAttribute("page",page);

        //4.����ת���� /pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }



}
