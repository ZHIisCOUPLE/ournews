package com.our.news.admin.web.controller;


import com.our.news.admin.service.AdminService;
import com.our.news.admin.service.UserService;
import com.ournews.commons.constant.ConstantUtils;
import com.ournews.commons.utils.CookieUtils;
import com.ournews.domain.entity.AdminUser;
import com.ournews.domain.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;


    /**
     * 跳转登录
     * @return
     */
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String Login(HttpServletRequest request,HttpServletResponse response){
        String userinfo = CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_NAME_ADMIN_INFO);
        String cookiecheck = CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_LOGIN_ISCHECK);
        if (!StringUtils.isBlank(cookiecheck)){
            String[] checkArray=cookiecheck.split(":");
            String admin=checkArray[0];
            String password=checkArray[1];
            boolean isCheck="1".equals(checkArray[2])?true:false;
            if (isCheck){
                AdminUser adminUser = adminService.adminLogin(admin, password);
                if (adminUser!=null){
                    return "redirect:/main";
                }else {
                    CookieUtils.deleteCookie(request,response,ConstantUtils.COOKIE_LOGIN_ISCHECK);
                    return Login(request,response);
                }
            }else {
                CookieUtils.deleteCookie(request,response,ConstantUtils.COOKIE_LOGIN_ISCHECK);
                return Login(request,response);
            }
        }
        if (!StringUtils.isBlank(userinfo)){
            String[] userArray=userinfo.split(":");
            String admin=userArray[0];
            String password=userArray[1];
            request.setAttribute("admin",admin);
            request.setAttribute("password",password);
            request.setAttribute("isRemember",true);
        }
        return "login";
    }

    /**
     * 登录逻辑
     * @param admin
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String admin, @RequestParam(required = true) String password, String remeber,String relogin, HttpServletRequest request, HttpServletResponse response){
        AdminUser adminUser = adminService.adminLogin(admin, password);
        System.out.println(adminUser);
        if (adminUser==null){
            request.setAttribute("msg","登录失败,邮箱或密码错误");
            return Login(request,response);
        }
        else {
            if (remeber!=null){
                CookieUtils.setCookie(request,response,ConstantUtils.COOKIE_NAME_ADMIN_INFO,String.format("%s:%s",admin,password),7*24*60*60);
            }else {
                CookieUtils.deleteCookie(request,response,ConstantUtils.COOKIE_NAME_ADMIN_INFO);
            }
            if (relogin!=null){
                CookieUtils.setCookie(request,response,ConstantUtils.COOKIE_LOGIN_ISCHECK,String.format("%s:%s:1",admin,password),7*24*60*60);
            }else {
                CookieUtils.deleteCookie(request,response,ConstantUtils.COOKIE_LOGIN_ISCHECK);
            }
            request.getSession().setAttribute(ConstantUtils.SESSION_ADMIN,adminUser);
            return "redirect:/main";
        }
    }
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response){
        CookieUtils.deleteCookie(request,response,ConstantUtils.COOKIE_LOGIN_ISCHECK);
        request.getSession().invalidate();
        return Login(request,response);
    }
}
