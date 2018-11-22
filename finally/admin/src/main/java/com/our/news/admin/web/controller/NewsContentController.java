package com.our.news.admin.web.controller;

import com.our.news.admin.service.NewInfoService;
import com.our.news.admin.service.NewsTypeService;
import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "content")
public class NewsContentController {
    @Autowired
    private NewInfoService newInfoService;
    @Autowired
    private NewsTypeService newsTypeService;

    /**
     * 根据id获取新闻内容
     * @param id
     * @return
     */
    @ModelAttribute//在每次@RequestMapping前都执行一次
    public NewsInfo getUser(Long id){
        NewsInfo newsInfo=null;
        //根据id获取对象
        if (id!=null){
            newsInfo=newInfoService.getNew(id);
        }
        //创建一个空对象
        else {
            newsInfo=new NewsInfo();
        }
        return newsInfo;
    }

    /**
     * 新闻信息列表跳转
     * @return
     */
    @RequestMapping(value = "news/list",method = RequestMethod.GET)
    public String geUser(Model model){
        List<NewsType> newsTypeList = newsTypeService.getNewsTypeList();
        model.addAttribute("newsTypes",newsTypeList);
        return "news_content_list";
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
            baseResult = newInfoService.deleteNew(id);
        }
        //批量删除
        else {
            baseResult = newInfoService.deleteMultiNew(ids);
        }
        return baseResult;
    }

    /**
     * 分页
     * @param request 请求
     * @param newsInfo 对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<NewsInfo> page(HttpServletRequest request, NewsInfo newsInfo){
        Map<String,Object> result=new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart=request.getParameter("start");
        String strLength=request.getParameter("length");

        int draw=strDraw==null?0:Integer.parseInt(strDraw);
        int start=strStart==null?0:Integer.parseInt(strStart);
        int length=strLength==null?10:Integer.parseInt(strLength);
        //返回的json信息
        PageInfo<NewsInfo> pageInfo = newInfoService.page(start, length, draw,newsInfo);

        return pageInfo;
    }
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(NewsInfo newsInfo){
        System.out.println(newsInfo);
        return "news_content_detail";
    }
    @RequestMapping(value = "news/form",method = RequestMethod.GET)
    public String form(Model model,NewsInfo newsInfo){
        List<NewsType> newsType=newsTypeService.getNewsTypeList();
        List<String> newsTypeString = new ArrayList<>();
        for (int i = 0; i < newsType.size(); i++) {
            newsTypeString.add(newsType.get(i).getName());
        }
        if(newsInfo.getType()!= null){
            newsTypeString.remove(newsInfo.getType());
        }
        model.addAttribute("NewsType",newsTypeString);
        return "news_content_form";
    }
    /**
     * 保存用户信息请求
     * @param newsInfo 对象
     * @param redirectAttributes 重定向信息保存(只保存一次,减少内存消耗)
     * @param model 转发信息保存(只保存一次,减少内存消耗)
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(NewsInfo newsInfo, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = newInfoService.save(newsInfo);
        redirectAttributes.addFlashAttribute("baseResult",baseResult);
        //保存成功
        if (baseResult.getStatus()==200){
            return "redirect:/content/news/list";//重定向
        }
        //保存失败
        else {
            redirectAttributes.addFlashAttribute("newsInfo",newsInfo);
            return "redirect:/content/news/form";//转发
        }
    }
}
