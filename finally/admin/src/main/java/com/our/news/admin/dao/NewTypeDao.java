package com.our.news.admin.dao;

import com.ournews.domain.entity.NewsType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ljs
 * @time 2018-10-31
 */
@Repository
public interface NewTypeDao {
    List<NewsType> getNewTypeList();
}
