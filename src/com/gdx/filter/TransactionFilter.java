package com.gdx.filter;

import com.gdx.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author ��껳�
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
            throw new RuntimeException(e);//���쳣�׸�Tomcatչʾ�ѺõĴ���ҳ��
        }
    }

    @Override
    public void destroy() {

    }
}
