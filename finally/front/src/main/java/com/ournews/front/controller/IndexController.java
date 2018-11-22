package com.ournews.front.controller;


import com.ournews.commons.persistence.BaseController;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;
import com.ournews.front.service.IndexService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController extends BaseController<IndexService, NewsInfo> {
    @Value("${fileImagesUrl}")
    private String IMAGES_PATH;

    @RequestMapping("")
    public String index(Model model, HttpSession session){
        //获取首页导航栏数据
        List<NewsType> newsTypes = service.getNewsType();
        model.addAttribute("ALLNEWSTYPE",newsTypes);
        session.setAttribute("NEWSTYPES",newsTypes);

        //获取 大新闻-右侧小新闻-底部小新闻
        Map<String,List<NewsInfo>> map = service.getClassifyNewsInfo();
        model.addAttribute("SLIDESHOWNEWS",map.get("SLIDESHOWNEWS"));
        model.addAttribute("BIGNEWS",map.get("BIGNEWS").get(0));
        model.addAttribute("RIGHTMINNEWS",map.get("RIGHTMINNEWS"));
        model.addAttribute("FOOTMINNEWS",map.get("FOOTMINNEWS"));
        return "index";
    }

    @RequestMapping(value = "getPicUrl", produces = "application/json;charset=utf8")
    @ResponseBody
    public String getNewsInfoPicUrl(Long id){
        String picUrls = service.getNewsInfoPicUrl(id);
        return picUrls;
    }


    /**
     * 远程获取图片链接
     * @param fileName
     * @param response
     */
    @RequestMapping("/picShow")
    public void picShow(String fileName, HttpServletResponse response, HttpServletRequest request){
        if (fileName==null || fileName == ""){
            return;
        }
        InputStream inputStream = null;
        try{
            //.jpg / .png 所以要加1
            String suffiix = fileName.substring(fileName.lastIndexOf(".")+1);

            if ("jpg".equals(suffiix)){
                response.setContentType("image/jpeg");
            }else if ("png".equals(suffiix)){
                response.setContentType("image/png");
            }
            inputStream = new FileInputStream(new File(IMAGES_PATH+fileName));
            OutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while (( len = inputStream.read(b)) > 0){
                outputStream.write(b,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @RequestMapping("/get")
    public String getPic(String fileName){
        return "redirect: /admin/picShow?fileName="+fileName;
    }




}
