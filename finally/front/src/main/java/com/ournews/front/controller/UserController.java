package com.ournews.front.controller;


import com.ournews.commons.persistence.BaseController;
import com.ournews.commons.persistence.Result;
import com.ournews.commons.utils.SendEmailUtil;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import com.ournews.front.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class UserController extends BaseController<UserService, User> {


    //跳转到登陆页面
    @RequestMapping("/login")
    public String getLongin(){
        return "user/login";
    }


    //登录验证
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String getAddLogin(User user, Long newsid, Model model, HttpSession session,HttpServletRequest request){

        if (newsid!=null){
            session.setAttribute("comgetnewsid",newsid);
        }


        Result result = service.getEmailAndPassword(user.getEmail(), user.getPassword());

        //成功
        if (result.getStatus()== Result.SUCCESS){
            model.addAttribute("USER_LOGIN",result.getData());
            session.setAttribute("USER_LOGIN",result.getData());

            Long comgetnewsid = (Long)session.getAttribute("comgetnewsid");
     //查看是否从详细页面跳转到登录页面
            if (comgetnewsid!=null){
                session.removeAttribute("comgetnewsid");
                return "redirect:/news?id="+comgetnewsid;

            }
            return "redirect:/";

        //失败
        }else {
            model.addAttribute("message",result.getMessage());
            //跳转到对应页面
            return "user/login";
        }


    }

    //调到用户注册页面
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "user/register";
    }

    /**
     * 用户注册
     * @param user
     * @param session
     * @param yxyz
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(User user,HttpSession session,String yxyz,Model model){
        String senemail = (String) session.getAttribute("senemail");
        if (yxyz.equals(senemail)){
            service.save(user);
            return "user/login";
        }else {
            model.addAttribute("message","验证码错误");
            return "user/toRegister";
        }

    }

    /**
     * 请求判断
     * @param user
     * @return
     */
    @RequestMapping(value = "/ajax",produces = "application/json;charset=utf8")
    @ResponseBody
    public String RegisterAjax(User user){
        Result result = service.RegisterAjax(user);
        return result.getMessage();
    }

    /**
     * 发邮件
     * @param email
     * @return
     */
    @RequestMapping(value = "/senemail",produces = "application/json;charset=utf8")
    @ResponseBody
    public String senemail(String email, HttpSession session){

        String s=null;
        try {
            s= SendEmailUtil.SendEmail(email);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        session.setAttribute("senemail",s);
        return s;
    }

    //注销用户
    @RequestMapping("/logout")
    public String getlogout(HttpSession session, HttpServletRequest request){
        session.removeAttribute("USER_LOGIN");
        return "redirect:/";
    }

    /**
     * 查询用户信息
     * @param session
     * @return
     */
    @RequestMapping("userinfo")
    public String userinfo(HttpSession session,Model model){
        User user= (User) session.getAttribute("USER_LOGIN");
        Result result = service.get(user.getId());
        List<NewsInfo> userNewsList = service.getUserNewsList(user.getId());
        model.addAttribute("userNewsList",userNewsList);
        model.addAttribute("Userinfo",result.getData());
        return "user/userinfo";
    }

    /**
     * 更新
     * @param user
     * @return
     */
    @RequestMapping(value = "userinfo",method = RequestMethod.POST)
    public String userinfo(User user,Model model){
        service.update(user);
        Result result = service.get(user.getId());
        model.addAttribute("Userinfo",result.getData());
        List<NewsInfo> userNewsList = service.getUserNewsList(user.getId());
        model.addAttribute("userNewsList",userNewsList);
        model.addAttribute("message","信息以保存");
        return "user/userinfo";
    }
}
