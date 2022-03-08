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
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/18 - 17:24
 */
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //调用bookService.queryBookId(id) 返回book对象得到图书信息
        Book book = bookService.queryBookById(id);

        //把图书信息转换为CaryItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        //调用Cart.addItem(CartItem);添加商品
        //把购物车类放到Session域中 如果不放到Session中，每次进入方法时创建一个cart对象那么商品总数永远是1
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {  //先判断cart是否为空，如果为空创建cart对象，不为空直接往cart对象中加入商品
            cart.addItem(cartItem);
        } else {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        //重定向回原来请求的页面
        resp.sendRedirect(req.getHeader("Referer"));

        //最后一个添加的商品名称保存到Session域中
        req.getSession().setAttribute("lastName", cartItem.getName());

    }


    /**
     * ajax异步请求完成加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //调用bookService.queryBookId(id) 返回book对象得到图书信息
        Book book = bookService.queryBookById(id);

        //把图书信息转换为CaryItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        //调用Cart.addItem(CartItem);添加商品
        //把购物车类放到Session域中 如果不放到Session中，每次进入方法时创建一个cart对象那么商品总数永远是1
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {  //先判断cart是否为空，如果为空创建cart对象，不为空直接往cart对象中加入商品
            cart.addItem(cartItem);
        } else {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        //最后一个添加的商品名称保存到Session域中
        req.getSession().setAttribute("lastName", cartItem.getName());

        //6.返回购物车总的商品数量和最后一个添加商品名称
        Map <String,Object> result=new HashMap<String,Object>();
        result.put("totalCount",cart.getTotalCount());
        result.put("lastName",cartItem.getName());

        Gson gson=new Gson();
        String jsonresult = gson.toJson(result);

        resp.getWriter().write(jsonresult);


    }


        /**
         * 清空购物车
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
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取Session域里的购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null) {
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 更改购买商品的数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
}
