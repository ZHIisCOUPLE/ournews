package com.ournews.front.dao;

import com.ournews.commons.persistence.BaseDAO;
import com.ournews.commons.persistence.Result;
import com.ournews.domain.entity.Comment;
import com.ournews.domain.entity.NewsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsInfoDao extends BaseDAO<NewsInfo> {
    /**   根据ID
     *   获取全部数据
     * @return
     */
    List<Comment> getList(@Param("id") Long id);

    void save(Comment comment);

    /**
     * 获取所有新闻
     * @return
     */
    List<NewsInfo> getAllNewsinfo();
	List<NewsInfo> getAllnews(Long id);

    /**
     * 根据类型ID 获取大(图片大的)新闻
     * @param id
     * @return
     */
    List<NewsInfo> getBigNewsByTypeId(@Param("id") Integer id);
    /**
     * 根据类型ID 获取小(图片小的)新闻
     * @param id
     * @return
     */
    List<NewsInfo> getMinNewsByType(@Param("id")Integer id);

    void upredcount(NewsInfo newsInfo);


//修改点赞数量
   void updateCommeIdNumber(@Param("commeID")Long commeID,@Param("likes")Long likes);

    /**
     * 搜索
     * @param seek
     * @return
     */
    List<NewsInfo> seeknews(@Param("seek")String seek);
}
