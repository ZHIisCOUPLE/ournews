package com.ournews.front.dao;

import com.ournews.commons.persistence.BaseDAO;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexDao extends BaseDAO<NewsInfo> {
    /**
     * 查询新闻类型
     * @return
     */
    List<NewsType> getNewsTypeList();

    /**
     * 根据ID 查询新闻的picture Url
     * @param id
     * @return
     */
    String getNewsInfoPicUrl(@Param("id") Long id);

    /**
     * 获取全部新闻
     * @return
     */
    @Override
    List<NewsInfo> getList();
}
