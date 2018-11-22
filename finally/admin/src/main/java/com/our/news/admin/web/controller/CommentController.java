package com.our.news.admin.web.controller;

import com.our.news.admin.service.CommentService;
import com.our.news.admin.service.UserService;
import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.Comment;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljs
 * @time 2018-10-31
 */
@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    /**
     * 跳转评论信息页面
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String geUser(Long id,Model model){
        model.addAttribute("idModel",id);
        return "news_content_comment";
    }

    /**
     * 评论分页
     * @param request 请求
     * @param comment 对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<Comment> page(HttpServletRequest request, Comment comment){
        Map<String,Object> result=new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart=request.getParameter("start");
        String strLength=request.getParameter("length");

        int draw=strDraw==null?0:Integer.parseInt(strDraw);
        int start=strStart==null?0:Integer.parseInt(strStart);
        int length=strLength==null?10:Integer.parseInt(strLength);
        //返回的json信息
        PageInfo<Comment> pageInfo = commentService.page(start, length, draw,comment);

        return pageInfo;
    }

    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String id){
        return commentService.deleteComment(id);
    }

//    @ResponseBody
//    @RequestMapping(value = "status",method = RequestMethod.POST)
//    public BaseResult status(HttpServletRequest req){
//        User user = userService.getUser(Long.parseLong(req.getParameter("id")));
//        return userService.status(""+user.getId(),""+user.getIsdelete());
//    }
}
