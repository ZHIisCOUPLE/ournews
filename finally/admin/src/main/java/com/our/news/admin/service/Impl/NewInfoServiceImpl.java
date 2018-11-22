package com.our.news.admin.service.Impl;

import com.our.news.admin.dao.CommentDao;
import com.our.news.admin.dao.NewInfoDao;
import com.our.news.admin.service.NewInfoService;
import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 新闻ServiceImpl
 * @author ljs
 * @time 2018-10-29
 */
@Service
public class NewInfoServiceImpl implements NewInfoService {

    @Autowired
    private NewInfoDao newInfoDao;
    @Autowired
    private CommentDao commentDao;

    /**
      * 数据分页(包括查询分页、基本的数据分页)
      * @author ljs
      */
    @Override
    public PageInfo page(int start, int length, int draw, NewsInfo newsInfo) {
        newsInfo.setType(getType(newsInfo.getType()));
        PageInfo<NewsInfo> newsInfoPageInfo = new PageInfo<>();
        newsInfoPageInfo.setDraw(draw);
        List<NewsInfo> newsInfoList = null;
        Long newsCount = 0L;
        if(newsInfo.getName() != null || (newsInfo.getType() != null && !newsInfo.getType().equals(""))){
            newsInfoList = newInfoDao.searchNewInfoList(start,length,newsInfo.getName(),newsInfo.getType());
            newsCount = newInfoDao.searchNewInfoCount(start,length,newsInfo.getName(),newsInfo.getType());
        }else{
            newsInfoList = newInfoDao.getNewInfoList(start,length);
            newsCount = newInfoDao.getNewInfoCount(start,length);
        }
        for (int i = 0 ; i < newsInfoList.size() ; i++){
            newsInfoList.get(i).setType(getType(newsInfoList.get(i).getType()));
        }
        newsInfoPageInfo.setRecordsTotal(newsCount.intValue());
        newsInfoPageInfo.setRecordsFiltered(newsCount.intValue());
        newsInfoPageInfo.setData(newsInfoList);
        return newsInfoPageInfo;
    }

    /**
      * 获取单个新闻
      * @author ljs
      */
    @Override
    public NewsInfo getNew(Long id) {
        NewsInfo newsInfo = newInfoDao.getNewInfoById(id);
        newsInfo.setType(getType(newsInfo.getType()));
        return newsInfo;
    }

    /**
      * 删除单个新闻
      * @author ljs
      */
    @Override
    public BaseResult deleteNew(String id) {
        BaseResult baseResult ;
        try{
            commentDao.deleteCommentByNewInfoId(Long.parseLong(id));
            newInfoDao.deleteNewInfo(Long.parseLong(id));
            baseResult = BaseResult.success("删除成功");
        } catch (Exception e){
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    /**
      * 批量删除新闻
      * @author ljs
      */
    @Override
    public BaseResult deleteMultiNew(String id) {
        BaseResult baseResult ;
        String[] ids = id.split(",");
        try{
            for (String s : ids) {
                commentDao.deleteCommentByNewInfoId(Long.parseLong(s));
            }
            newInfoDao.deleteMultiNew(ids);
            baseResult = BaseResult.success("删除成功");
        } catch (Exception e){
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    /**
      * 新增新闻以及修改新闻
      * @author ljs
      */
    @Override
    public BaseResult save(NewsInfo newsInfo) {
        BaseResult baseResult ;
        if(StringUtils.isBlank(newsInfo.getName())){
            baseResult = BaseResult.fail("标题不能为空");
            return baseResult;
        }else if(StringUtils.isBlank(newsInfo.getType())){
            baseResult = BaseResult.fail("类型不能为空");
            return baseResult;
        }else if(StringUtils.isBlank(newsInfo.getPicture())){
            baseResult = BaseResult.fail("图片不能为空");
            return baseResult;
        }else if(StringUtils.isBlank(newsInfo.getDetail())){
            baseResult = BaseResult.fail("内容不能为空");
            return baseResult;
        }else if(newsInfo.getName().length() > 50){
            baseResult = BaseResult.fail("标题不得超过50个字");
            return baseResult;
        }else if(newsInfo.getDetail().length() < 100){
            baseResult = BaseResult.fail("内容不得少于25个字");
            return baseResult;
        }
        newsInfo.setType(getType(newsInfo.getType()));
        Date updated = new Date();
        if(newsInfo.getId() != null){
            try{
                newsInfo.setUpdated(updated);
                newInfoDao.updateNewInfo(newsInfo);
                baseResult = BaseResult.success("修改成功");
            } catch(Exception e){
                baseResult = BaseResult.fail("修改失败");
            }
        }else{
            try{
                newsInfo.setUpdated(updated);
                newsInfo.setCreated(updated);
                User user = new User();
                user.setId(1L);
                newsInfo.setUser(user);
                newInfoDao.insertNewInfo(newsInfo);
                baseResult = BaseResult.success("添加成功");
            } catch(Exception e){
                baseResult = BaseResult.fail("添加失败");
            }
        }
        return baseResult;
    }

    @Override
    public List<NewsInfo> getList() {
        List<NewsInfo> list = newInfoDao.getList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setType(getType(list.get(i).getType()));
        }
        return list;
    }

    /**
      * 将数据库的类型转换成页面所需字符
      * 将页面数据转换成数据库需要的类型
      * @author ljs
      */
    @Override
    public String getType(String type){
        if(type == null )
            return "";
        switch (type){
            case "1": return "热点";
            case "2": return "科技";
            case "3": return "汽车";
            case "4": return "财经";
            case "5": return "国外";
            case "6": return "游戏";
            case "7": return "军事";
            case "8": return "搞笑";
            case "9": return "体育";
            case "热点": return "1";
            case "科技": return "2";
            case "汽车": return "3";
            case "财经": return "4";
            case "国外": return "5";
            case "游戏": return "6";
            case "军事": return "7";
            case "搞笑": return "8";
            case "体育": return "9";
        }
        return "";
    }
}
