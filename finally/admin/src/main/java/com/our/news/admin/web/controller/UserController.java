package com.our.news.admin.web.controller;

import com.our.news.admin.service.UserService;
import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    /**
     * 根据@Autowired自动构造对象
     */
    @Autowired
    private UserService userService;

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @ModelAttribute//在每次@RequestMapping前都执行一次
    public User getUser(Long id){
        User user=null;
        //根据id获取对象
        if (id!=null){
            user=userService.getUser(id);
        }
        //创建一个空对象
        else {
            user=new User();
        }
        return user;
    }

    /**
     * 用户信息列表跳转
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String geUser(){
        return "user_list";
    }

    /**
     * 删除
     * @param ids 根据“,”拼接起来的id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids, HttpServletRequest request){
        BaseResult baseResult=null;
        //ids为空时为删除
        if (StringUtils.isBlank(ids)){
            String id = request.getParameter("id");
            baseResult = userService.delete(id);
        }
        //批量删除
        else {
            baseResult = userService.deleteMulti(ids);
        }
        return baseResult;
    }

    /**
     * 分页
     * @param request 请求
     * @param user 对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<User> page(HttpServletRequest request, User user){
        Map<String,Object> result=new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart=request.getParameter("start");
        String strLength=request.getParameter("length");

        int draw=strDraw==null?0:Integer.parseInt(strDraw);
        int start=strStart==null?0:Integer.parseInt(strStart);
        int length=strLength==null?10:Integer.parseInt(strLength);
        //返回的json信息
        PageInfo<User> pageInfo = userService.page(start, length, draw,user);

        return pageInfo;
    }
    @ResponseBody
    @RequestMapping(value = "status",method = RequestMethod.POST)
    public BaseResult status(HttpServletRequest request){
        String id = request.getParameter("id");
        String isdelete = request.getParameter("isdelete");
        BaseResult baseResult=userService.status(id,isdelete);
        return baseResult;
    }

}
