package com.gdx.web;

import com.gdx.bean.Book;
import com.gdx.bean.Cart;
import com.gdx.bean.CartItem;
import com.gdx.service.BookService;
import com.gdx.service.impl.BookServiceImpl;
import com.gdx.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/18 - 17:24
 */
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * ���빺�ﳵ
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ����Ĳ��� ��Ʒ���
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //����bookService.queryBookId(id) ����book����õ�ͼ����Ϣ
        Book book = bookService.queryBookById(id);

        //��ͼ����Ϣת��ΪCaryItem��Ʒ��
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        //����Cart.addItem(CartItem);�����Ʒ
        //�ѹ��ﳵ��ŵ�Session���� ������ŵ�Session�У�ÿ�ν��뷽��ʱ����һ��cart������ô��Ʒ������Զ��1
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {  //���ж�cart�Ƿ�Ϊ�գ����Ϊ�մ���cart���󣬲�Ϊ��ֱ����cart�����м�����Ʒ
            cart.addItem(cartItem);
        } else {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        //�ض����ԭ�������ҳ��
        resp.sendRedirect(req.getHeader("Referer"));

        //���һ����ӵ���Ʒ���Ʊ��浽Session����
        req.getSession().setAttribute("lastName", cartItem.getName());

    }


    /**
     * ajax�첽������ɼ��빺�ﳵ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ����Ĳ��� ��Ʒ���
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //����bookService.queryBookId(id) ����book����õ�ͼ����Ϣ
        Book book = bookService.queryBookById(id);

        //��ͼ����Ϣת��ΪCaryItem��Ʒ��
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        //����Cart.addItem(CartItem);�����Ʒ
        //�ѹ��ﳵ��ŵ�Session���� ������ŵ�Session�У�ÿ�ν��뷽��ʱ����һ��cart������ô��Ʒ������Զ��1
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {  //���ж�cart�Ƿ�Ϊ�գ����Ϊ�մ���cart���󣬲�Ϊ��ֱ����cart�����м�����Ʒ
            cart.addItem(cartItem);
        } else {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        //���һ����ӵ���Ʒ���Ʊ��浽Session����
        req.getSession().setAttribute("lastName", cartItem.getName());

        //6.���ع��ﳵ�ܵ���Ʒ���������һ�������Ʒ����
        Map <String,Object> result=new HashMap<String,Object>();
        result.put("totalCount",cart.getTotalCount());
        result.put("lastName",cartItem.getName());

        Gson gson=new Gson();
        String jsonresult = gson.toJson(result);

        resp.getWriter().write(jsonresult);


    }


        /**
         * ��չ��ﳵ
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null) {
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * ɾ����Ʒ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ����Ĳ��� ��Ʒ���
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //��ȡSession����Ĺ��ﳵ����
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null) {
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * ���Ĺ�����Ʒ������
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ����Ĳ��� ��Ʒ���
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
}
