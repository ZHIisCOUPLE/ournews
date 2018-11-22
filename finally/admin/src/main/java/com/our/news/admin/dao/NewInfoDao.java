package com.our.news.admin.dao;

import com.ournews.domain.entity.NewsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hh
 * @time 2018-10-29
 */
@Repository
public interface NewInfoDao {

    NewsInfo getNewInfoById(Long id);

    List<NewsInfo> getNewInfoList(@Param("start") int start, @Param("length") int length);

    List<NewsInfo> searchNewInfoList(@Param("start") int start, @Param("length") int length,@Param("name") String name,@Param("type") String type);

    void deleteMultiNew(String[] ids);

    void updateNewInfo(NewsInfo newsInfo);

    void updateNewInfoCommentCount(@Param("id")String id,@Param("commentCount")int commentCount);

    void insertNewInfo(NewsInfo newsInfo);

    void deleteNewInfo(Long id);

    Long getNewInfoCount(@Param("start") int start, @Param("length") int length);

    Long searchNewInfoCount(@Param("start") int start, @Param("length") int length,@Param("name") String name,@Param("type") String type);

    List<NewsInfo> getList();
}
