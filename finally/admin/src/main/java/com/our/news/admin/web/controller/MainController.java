package com.our.news.admin.web.controller;

import com.our.news.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {
    @Autowired
    private UserService userService;
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String Main(){
        return "main";
    }
}
