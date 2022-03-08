package com.gdx.web;

import com.gdx.bean.User;
import com.gdx.service.UserService;
import com.gdx.service.impl.UserServiceImpl;
import com.gdx.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/1 - 9:55
 */
public class UserServlet extends BaseServlet {
   private UserService userService=new UserServiceImpl();
    /**
     * ��¼����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.��ȡ�������
        User user= WebUtils.copyParamToBean(req.getParameterMap(),new User());

        //2.����service.xxx()
        User loginUser=userService.login(new User(null,user.getUsername(),user.getPassword(),null));
        //3.����login()�������ؽ���ж��Ƿ��¼�ɹ�

        if (loginUser== null){
            //�Ѵ�����Ϣ���ͻ��Եı���Ϣ�����浽req����
            req.setAttribute("msg", "�û������������!");
            req.setAttribute("username", user.getUsername());
            //ʧ�ܷ��ص�¼����
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //�����û���¼��Ϣ��session����
            req.getSession().setAttribute("user",loginUser);
            //�ɹ����ص�¼�ɹ�ҳ��
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    /**
     * ע�Ṧ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡsession�е���֤��
        String key= (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //ɾ��session�е���֤��
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1.��ȡ�ͻ����������֤��
        String code = req.getParameter("code");

        User user= WebUtils.copyParamToBean(req.getParameterMap(),new User());

        //2.�����֤���Ƿ���ȷ  д�����  Ҫ����֤��Ϊabcde
        if(key!=null && key.equalsIgnoreCase(code)){
            //3.��ȷ  ����û����Ƿ����
            if(userService.existsUsername(user.getUsername())){//���ڷ���true ��ʾ�û���������
                //4.������
                //����ע��ҳ��
                req.setAttribute("msg","�û����Ѵ���!");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("password",user.getPassword());
                req.setAttribute("email",user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //5.���� ����service���浽���ݿ�
                userService.registerUser(new User(null,user.getUsername(),user.getPassword(),user.getEmail()));
                //����ע��ɹ�ҳ��  regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }else {
            //6.����ȷ
            //����ע��ҳ��
            req.setAttribute("msg","��֤�����!");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("password",user.getPassword());
            req.setAttribute("email",user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }

    /**
     * ע���û�������ҳ (���¼ҳ��)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ɾ����ǰ�Ự
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }



    protected void managerLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.��ȡ�������
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        User loginUser=userService.login(new User(null,user.getUsername(),user.getPassword(),null));
        //3.����login()�������ؽ���ж��Ƿ��¼�ɹ�
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(username) && "admin".equals(password)) {
            //��������¼��Ϣ��session����
            req.getSession().setAttribute("admin",username);
            req.getSession().setAttribute("password",password);

            req.getSession().setAttribute("user",loginUser);

            //�ɹ����ص�¼�ɹ�ҳ��
            req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req, resp);
        } else {
            //�Ѵ�����Ϣ���ͻ��Եı���Ϣ�����浽req����
            req.setAttribute("msg", "����Ա�˺Ż��������!");
            req.setAttribute("username", username);
            //ʧ�ܷ��ص�¼����
            req.getRequestDispatcher("/pages/manager/manager_login.jsp").forward(req, resp);
        }
    }


    /**
     * ʧȥ����  �ж��û����Ƿ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ�������
        String username = req.getParameter("username");
        //����userService.existsUsername
        boolean existsUsername = userService.existsUsername(username);

        //�ѷ��صĽ����װ��Ϊmap����
        Map<String,Object> result = new HashMap<>();
        result.put("existsUsername",existsUsername);
        //����gson jar��
        //��result���Ϸ�װ��json�ַ���
        Gson gson=new Gson();
        String jsonresult = gson.toJson(result);

        //���ظ��ͻ���
        resp.getWriter().write(jsonresult);
    }
}
