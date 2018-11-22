package com.ournews.front.service;

import com.ournews.commons.persistence.BaseService;
import com.ournews.commons.persistence.Result;
import com.ournews.domain.entity.Comment;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsInfoService extends BaseService<NewsInfo> {


    List<Comment> getcomment(Long newInfo_id);

//    /**
//     * 获取所有新闻
//     * @return
//     */
//    List<NewsInfo> getAllNewsinfo();

    Result save(User user, String text, Long id);

    /**
     * 根据传入新闻类型的ID 获取大新闻
     * @param id
     * @return
     */
    List<NewsInfo> getBigNewsByType(Integer id);
	List<NewsInfo> getAllnews(Long id);
    /**
     *  根据传入新闻类型的ID 获取小新闻
     * @param id
     * @return
     */
    List<NewsInfo> getMinNewsByType(Integer id);

    /**
     * 修改新闻阅读数量
     *
     */
    void upredcount(NewsInfo newsInfo);

    /**
     *           修改点赞数量
     * @param commeID
     * @param likes
     * @return
     */
    void updateCommeIdNumber(Long commeID,Long likes);

    List<NewsInfo> seeknews(String seek);
}
