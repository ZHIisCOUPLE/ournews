package com.ournews.front.dao;

import com.ournews.commons.persistence.BaseDAO;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddNewsDao extends BaseDAO<NewsInfo> {
    List<NewsType> getType();

    NewsInfo getNewsByNewsNameAndUserId(@Param("newsName") String newsName, @Param("id") Long id);
}
