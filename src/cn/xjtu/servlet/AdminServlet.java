package cn.xjtu.servlet;

import cn.xjtu.entity.Admin;
import cn.xjtu.exception.UserExistsException;
import cn.xjtu.service.IAdminService;
import cn.xjtu.service.impl.AdminService;

import java.io.IOException;

/**
 *
 */
@javax.servlet.annotation.WebServlet(name = "AdminServlet",urlPatterns = {"/admin"})
public class AdminServlet extends javax.servlet.http.HttpServlet {
    //调用service
    private IAdminService adminService = new AdminService();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //获取操作类型
        String method = request.getParameter("method");
        if ("register".equals(method)){
            register(request,response);
        }
    }

    /**
     * 注册处理方法
     * @param request
     * @param response
     */
    private void register(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        //1.获取请求参数
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //2.封装
        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPassword(password);
        //3.调用Service处理注册的业务逻辑
        try {
            adminService.register(admin);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } catch (UserExistsException e) {
            //用户名存在了，注册失败 跳转到注册页面
            request.setAttribute("message","用户名已经存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);

        } catch (Exception e){
            //其他错误,跳转到错误页面
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/error/error.jsp");
        }
    }
}
