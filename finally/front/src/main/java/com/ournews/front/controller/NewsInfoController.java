package com.ournews.front.controller;


import com.ournews.commons.persistence.BaseController;
import com.ournews.commons.persistence.Result;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import com.ournews.front.service.NewsInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NewsInfoController extends BaseController<NewsInfoService, NewsInfo> {

//拿到新闻并跳转到详情页
    @RequestMapping("/news")
    public String getnews(@RequestParam(value = "id",defaultValue = "0") Long id, Model model){

        if (id!=0){
            Result result = service.get(id);
            NewsInfo newsInfo=(NewsInfo)result.getData();

            if (newsInfo!=null){
                newsInfo.setReadCount(newsInfo.getReadCount()+1);
                service.upredcount(newsInfo);
            }
            //拿到小编所有的新闻
            List<NewsInfo> allnews = service.getAllnews(newsInfo.getUser().getId());

            model.addAttribute("newsinfo",result.getData());
            model.addAttribute("allnews",allnews);
            return "news";
        }else {
            return "redirect:/";
        }

    }



//添加评论
    @RequestMapping("/addComment")
    public String getaddComment(String text, Long id, HttpServletRequest request, HttpSession session, Model model){
   //拿到登陆用户
        User userLogin = (User)session.getAttribute("USER_LOGIN");
 //判断用户是否已经登陆
        if (userLogin!=null){
            Result result = service.save(userLogin, text, id);
            //成功
            if (result.getStatus()==Result.SUCCESS){
        //拿该到新闻
                NewsInfo newsInfo = (NewsInfo)service.get(id).getData();
        //修改评论数量
                newsInfo.setCommentCount(newsInfo.getCommentCount()+1);
                service.update(newsInfo);
                return "redirect:/news?id="+id;

             //失败
            }else {
                return "redirect:/news";
            }

         //没有登录
        }else {
            model.addAttribute("newsid",id);
            model.addAttribute("message","请先进行登陆再评论！");
            return "user/login";
        }
    }

    /**
     * 用户删除新闻
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String Deletenews(Long id, HttpSession session){
        NewsInfo newsInfo= new NewsInfo();
        newsInfo.setId(id);
        service.delete(newsInfo);
        return "redirect:userinfo";
    }

    /**
     * 用ajax进行请求判断
     * @param
     * @return
     */
    @RequestMapping(value = "/comNumber",method = RequestMethod.POST)
    @ResponseBody
    public void RegisterAjax(Long commeID,Long likes){
        service.updateCommeIdNumber(commeID,likes);

    }

    /**
     * 搜索新闻
     * @param seek
     * @return
     */
    @RequestMapping("/seeknews")
    public String seeknews(String seek,HttpSession session){
        if(seek.equals(null)){
            return "typeNews/seeknews";
        }else {
            List<NewsInfo> infos = service.seeknews(seek);
            session.setAttribute("infos",infos);
            return "typeNews/seeknews";
        }

    }
}
