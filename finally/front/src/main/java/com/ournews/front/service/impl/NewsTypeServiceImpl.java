package com.ournews.front.service.impl;

import com.ournews.commons.persistence.BaseServiceImpl;
import com.ournews.domain.entity.NewsType;
import com.ournews.front.dao.NewsTypeDao;
import com.ournews.front.service.NewsTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsTypeServiceImpl extends BaseServiceImpl<NewsType, NewsTypeDao> implements NewsTypeService {


    @Override
    public List<NewsType> getList() {
        return dao.getList();
    }
}
