package com.our.news.admin.service;

import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.NewsInfo;

import java.util.List;

/**
 * @author ljs
 * @time 2018-10-29
 */
public interface NewInfoService {

    PageInfo page(int start, int length, int draw, NewsInfo newsInfo);
    NewsInfo getNew(Long id);
    BaseResult deleteNew(String id);
    BaseResult deleteMultiNew(String ids);
    BaseResult save(NewsInfo newsInfo);
    List<NewsInfo> getList();
    String getType(String type);
}
