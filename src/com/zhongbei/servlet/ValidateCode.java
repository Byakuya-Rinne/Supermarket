package com.zhongbei.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/verifycode")
public class ValidateCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 设置大小 heigth  width
        int height = 100;
        int width = 50;

        //2 创建画面对象image  参数： heigth  width 颜色类型
        BufferedImage image = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

        //设置图片背景色
        //2.1设置画笔对象填充颜色
        Graphics g = image.getGraphics();
        //2.1.1填充背景色
        g.setColor(Color.PINK);
        g.fillRect(0, 0, width * 2, height);

        // 2.1.2写验证码
        g.setColor(Color.BLUE);
        String str = "ABCDEFGHIJKMNOPQRSTWXYZabacdefghijkmnopqrstwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < 5; i++) {
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            g.drawString(ch + "", width / 5 * i, 25);
            sb.append(ch);
        }

        //存储后台生成的验证码
        String verifyCode = sb.toString();
        System.out.println("");
        System.out.println("");
        System.out.println("ValidateCode Servlet生成的验证码: " + verifyCode);
        req.getSession().setAttribute("verifycode", verifyCode);

        //	3 向页面相应 ImageIO.write(image,"jpg",response.getOutputStream())
        ImageIO.write(image, "jpg", resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
