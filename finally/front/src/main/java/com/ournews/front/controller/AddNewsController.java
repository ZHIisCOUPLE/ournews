package com.ournews.front.controller;

import com.alibaba.fastjson.JSON;
import com.ournews.commons.persistence.BaseController;
import com.ournews.commons.persistence.Result;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;
import com.ournews.domain.entity.User;
import com.ournews.front.service.AddNewsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * 用户添加新闻
 */


@Controller
public class AddNewsController extends BaseController<AddNewsService, NewsInfo> {

    @Value("${fileImagesUrl}")
    private String IMAGES_PATH;
    /**
     * 跳转到添加新闻页面
     * @return
     */
    @RequestMapping("/addnewspage")
    public String getAddNewspage(HttpSession session, Model model){
//拿到登录用户
        User userLogin = (User)session.getAttribute("USER_LOGIN");
//判断有无登录
        if (userLogin!=null){
            NewsInfo newsInfo = new NewsInfo();
            model.addAttribute("newsInfo", newsInfo);

       //拿到所有新闻类型
            List<NewsType> newsTypesFrest = service.getType();
            List<String> newsTypes=new ArrayList<>();

            for (NewsType newsType : newsTypesFrest) {
                newsTypes.add(newsType.getName());
            }

            model.addAttribute("newsTypes",newsTypes);

            return "addnews";
        }else {
            model.addAttribute("message","请先登录再进行发布新闻！");
            return "user/login";
        }


    }

    @RequestMapping("/addnews")
    public String getsave(Model model, HttpSession session, NewsInfo newsInfo, RedirectAttributes redirectAttributes) {
        User userLogin = (User) session.getAttribute("USER_LOGIN");
        newsInfo.setUser(userLogin);
        Result result = service.save(newsInfo);


        //发布成功跳转到新闻详情页
        if (result.getStatus() == Result.SUCCESS) {
            NewsInfo newsInfo1 = service.getNewsByNewsNameAndUserId(newsInfo.getName(),userLogin.getId());
            //待用
            return "redirect:/news?id="+newsInfo1.getId();

            //失败跳转到发布新闻页
        } else {
            model.addAttribute("message", "发布失败！请重新编辑！");

            return "addnews";
        }


    }

    /**
     * MultipartFile   的属性file代表了上传的表单域的name
     * @param dropFile
     * @return
     */
    @ResponseBody
    @RequestMapping("/news/upload")
    public String uplod(MultipartFile dropFile, HttpServletResponse response, HttpServletRequest request)throws Exception{
        response.setContentType("application/json;charset=utf-8");//指定返回数据的类型是json
        //获取原来的文件名
        String fileName=dropFile.getOriginalFilename();
        int index=fileName.lastIndexOf(".");
        String suffix=fileName.substring(index);
        fileName= UUID.randomUUID().toString().replaceAll("-","")+suffix;
        dropFile.transferTo(new File(IMAGES_PATH + fileName));
        Map data=new HashMap();
        data.put("errno",0);
        data.put("data",new String[]{"/picShow?fileName="+fileName});
        data.put("path",fileName);
        return JSON.toJSONString(data);
    }



}
