package com.our.news.admin.service.Impl;

import com.our.news.admin.dao.NewTypeDao;
import com.our.news.admin.service.NewsTypeService;
import com.ournews.domain.entity.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻类型ServiceImpl
 * @author ljs
 * @time 2018-10-31
 */
@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewTypeDao newTypeDao;

    /**
      * 获取所有类型
      * @author ljs
      */
    @Override
    public List<NewsType> getNewsTypeList() {
        return newTypeDao.getNewTypeList();
    }
}
