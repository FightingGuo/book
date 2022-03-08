package com.gdx.filter;

import com.gdx.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/3/4 - 14:36
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
