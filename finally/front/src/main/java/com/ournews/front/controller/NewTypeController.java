package com.ournews.front.controller;

import com.ournews.commons.persistence.BaseController;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;
import com.ournews.front.service.NewsInfoService;
import com.ournews.front.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NewTypeController extends BaseController<NewsTypeService,NewsType> {
    @Autowired
    private NewsInfoService newsInfoService;
    //跳转到 分类/类型 对应的详细页面
    @RequestMapping("/toTypeNews")
    public String toTypeNewsShow(Integer id, Model model){
        //根据传入的新闻类型ID 获取当前类型的大新闻
        List<NewsInfo> bigNewsInfos = newsInfoService.getBigNewsByType(id);
        if (bigNewsInfos.size() > 0){
            model.addAttribute("BIGNEWSINFOS",bigNewsInfos);
        }
        //根据传入的id获取小新闻
        List<NewsInfo> minNewsInfos = newsInfoService.getMinNewsByType(id);

        if (minNewsInfos != null){
            model.addAttribute("MINNEWSINFOS",minNewsInfos);
        }
        return "typeNews/classifyNews";
    }





}
