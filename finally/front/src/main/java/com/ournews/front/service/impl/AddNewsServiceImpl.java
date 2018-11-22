package com.ournews.front.service.impl;

import com.ournews.commons.persistence.BaseServiceImpl;
import com.ournews.commons.persistence.Result;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;
import com.ournews.front.dao.AddNewsDao;
import com.ournews.front.service.AddNewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddNewsServiceImpl extends BaseServiceImpl<NewsInfo, AddNewsDao> implements AddNewsService {
    //获得所有类型
    @Override
    public List<NewsType> getType() {
        List<NewsType> newsTypes = dao.getType();
        return newsTypes;
    }

    @Override
    public NewsInfo getNewsByNewsNameAndUserId(String newsName, Long id) {
        return dao.getNewsByNewsNameAndUserId(newsName,id);
    }

    @Override
    public Result save(NewsInfo newsInfo) {
        newsInfo.setType(getType(newsInfo.getType()));
        return super.save(newsInfo);
    }

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
            case "热点": return "1";
            case "科技": return "2";
            case "汽车": return "3";
            case "财经": return "4";
            case "国外": return "5";
            case "游戏": return "6";
            case "军事": return "7";
        }
        return "";
    }
}
