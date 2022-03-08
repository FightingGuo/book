package com.gdx.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/27 - 20:32
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //1.获取Filter名称filter-name的内容
//        System.out.println("filter-name的值为"+filterConfig.getFilterName());
        //2.获取Filter中配置的init-param初始化参数
//        System.out.println("init-param初始化username的值为"+filterConfig.getInitParameter("username"));
//        System.out.println("init-param初始化参数url为"+filterConfig.getInitParameter("url"));
        //3.获取ServletContext对象
//        System.out.println("ServletContext对象为"+filterConfig.getServletContext());
    }

    /**
     * doFilter方法，专门用于拦截请求。可以做权限检查
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpSession session=httpServletRequest.getSession();

        Object user = session.getAttribute("user");
        if(user==null){
            servletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
            return;
        }else {
            //让程序继续往下访问用户的目标资源
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
