package com.gdx.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/27 - 20:32
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //1.��ȡFilter����filter-name������
//        System.out.println("filter-name��ֵΪ"+filterConfig.getFilterName());
        //2.��ȡFilter�����õ�init-param��ʼ������
//        System.out.println("init-param��ʼ��username��ֵΪ"+filterConfig.getInitParameter("username"));
//        System.out.println("init-param��ʼ������urlΪ"+filterConfig.getInitParameter("url"));
        //3.��ȡServletContext����
//        System.out.println("ServletContext����Ϊ"+filterConfig.getServletContext());
    }

    /**
     * doFilter������ר�������������󡣿�����Ȩ�޼��
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
            //�ó���������·����û���Ŀ����Դ
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
