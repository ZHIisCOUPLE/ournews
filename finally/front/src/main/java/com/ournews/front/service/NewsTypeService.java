package com.ournews.front.service;

import com.ournews.commons.persistence.BaseService;
import com.ournews.domain.entity.NewsType;

import java.util.List;

public interface NewsTypeService extends BaseService<NewsType> {


    List<NewsType> getList();
}
