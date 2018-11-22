package com.ournews.front.service;

import com.ournews.commons.persistence.BaseService;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;

import java.util.List;

public interface AddNewsService extends BaseService<NewsInfo> {
    List<NewsType> getType();

    NewsInfo getNewsByNewsNameAndUserId(String newsName, Long id);
}
