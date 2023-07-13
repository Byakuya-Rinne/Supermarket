package com.zhongbei.servlet;

import com.zhongbei.servlet.entity.User;
import com.zhongbei.servlet.service.IRegisterService;
import com.zhongbei.servlet.service.impl.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//注册帐号
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private String username;
    private String password;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        //获取用户输入的用户名和密码
        username = req.getParameter("username");
        password = req.getParameter("password");

        //后台显示用户输入的用户名和密码
        System.out.println("用户输入的username: "+username);
        System.out.println("用户输入的password: "+password);

        //获取ValidateCode servlet生成的验证码
        String generatedVerifyCode = (String) req.getSession().getAttribute("verifycode");
        System.out.println("RegisterServlet获取到后台的验证码: "+generatedVerifyCode);

        //获取用户输入的验证码
        String userVerifyCode = req.getParameter("verifycode");
        System.out.println("用户输入的验证码: " + userVerifyCode);

        //验证用户输入验证码和ValidateCode生成的验证码是否匹配
        if (!userVerifyCode.equalsIgnoreCase(generatedVerifyCode)) {
            // 验证码不匹配
            resp.getWriter().println("验证码错误");
            System.out.println("用户验证码输入错误");
            //重定向
//            resp.sendRedirect("/Supermarket/Register.html");
        }else if(userVerifyCode.equalsIgnoreCase(generatedVerifyCode)){
            System.out.println("用户验证码输入正确");
            resp.getWriter().println("验证码输入正确");
            saveUser(resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public void saveUser(HttpServletResponse resp) throws IOException {
        /*
         * 1、判断用户名和密码是否为空
         * 2、一般会前端校验：用户名是否正确
         * 3、进行数据业务存储
         * 4、返回成功或失败
         * 5、开始调用业务端
         */
        User user = new User(username,password);
        System.out.println("=============校验成功，开始调用 service 进行数据存储=============");
        IRegisterService registerService = new RegisterServiceImpl();
        boolean result = registerService.saveUser(user);
        System.out.println("saveUser成功");
        if (!result) {
            // 返回到注册页面
            System.out.println("注册失败，请重新注册");
            resp.sendRedirect("/Supermarket/register.html");
        }

        resp.sendRedirect("/Supermarket/login.html");

    }



}
