package com.ournews.front.service;

import com.ournews.commons.persistence.BaseService;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;

import java.util.List;
import java.util.Map;

public interface IndexService extends BaseService<NewsInfo> {

    /**
     * 查询新闻类型
     * @return
     */
    List<NewsType> getNewsType();


    /**
     * 根据ID 查询新闻的picture Url
     * @param id
     * @return
     */
    String getNewsInfoPicUrl(Long id);





    /**
     * 版本迭代，优化首页获取新闻的查询次数
     * @return
     */
    Map<String, List<NewsInfo>> getClassifyNewsInfo();
}
