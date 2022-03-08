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
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/1 - 9:55
 */
public class UserServlet extends BaseServlet {
   private UserService userService=new UserServiceImpl();
    /**
     * 登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        User user= WebUtils.copyParamToBean(req.getParameterMap(),new User());

        //2.调用service.xxx()
        User loginUser=userService.login(new User(null,user.getUsername(),user.getPassword(),null));
        //3.根据login()方法返回结果判断是否登录成功

        if (loginUser== null){
            //把错误信息，和回显的表单信息，保存到req域中
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", user.getUsername());
            //失败返回登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //保存用户登录信息到session域中
            req.getSession().setAttribute("user",loginUser);
            //成功返回登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    /**
     * 注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session中的验证码
        String key= (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1.获取客户端输入的验证码
        String code = req.getParameter("code");

        User user= WebUtils.copyParamToBean(req.getParameterMap(),new User());

        //2.检查验证码是否正确  写死检查  要求验证码为abcde
        if(key!=null && key.equalsIgnoreCase(code)){
            //3.正确  检查用户名是否可用
            if(userService.existsUsername(user.getUsername())){//存在返回true 表示用户名不可用
                //4.不可用
                //跳回注册页面
                req.setAttribute("msg","用户名已存在!");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("password",user.getPassword());
                req.setAttribute("email",user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //5.可用 调用service保存到数据库
                userService.registerUser(new User(null,user.getUsername(),user.getPassword(),user.getEmail()));
                //跳到注册成功页面  regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }else {
            //6.不正确
            //跳回注册页面
            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("password",user.getPassword());
            req.setAttribute("email",user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }

    /**
     * 注销用户返回首页 (或登录页面)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //删除当前会话
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }



    protected void managerLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        User loginUser=userService.login(new User(null,user.getUsername(),user.getPassword(),null));
        //3.根据login()方法返回结果判断是否登录成功
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(username) && "admin".equals(password)) {
            //保存管理登录信息到session域中
            req.getSession().setAttribute("admin",username);
            req.getSession().setAttribute("password",password);

            req.getSession().setAttribute("user",loginUser);

            //成功返回登录成功页面
            req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req, resp);
        } else {
            //把错误信息，和回显的表单信息，保存到req域中
            req.setAttribute("msg", "管理员账号或密码错误!");
            req.setAttribute("username", username);
            //失败返回登录界面
            req.getRequestDispatcher("/pages/manager/manager_login.jsp").forward(req, resp);
        }
    }


    /**
     * 失去焦点  判断用户名是否可用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        //调用userService.existsUsername
        boolean existsUsername = userService.existsUsername(username);

        //把返回的结果封装成为map对象
        Map<String,Object> result = new HashMap<>();
        result.put("existsUsername",existsUsername);
        //导入gson jar包
        //把result集合封装成json字符串
        Gson gson=new Gson();
        String jsonresult = gson.toJson(result);

        //返回给客户端
        resp.getWriter().write(jsonresult);
    }
}
