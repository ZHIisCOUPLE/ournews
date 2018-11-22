package com.ournews.front.service.impl;

import com.ournews.commons.persistence.BaseServiceImpl;
import com.ournews.commons.persistence.Result;
import com.ournews.domain.entity.Comment;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import com.ournews.front.dao.NewsInfoDao;
import com.ournews.front.service.NewsInfoService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class NewsInfoServiceImpl extends BaseServiceImpl<NewsInfo, NewsInfoDao> implements NewsInfoService {


    @Override
    public Result get(Long id) {
        //根据ID拿到新闻详情
        NewsInfo newsInfo = dao.get(id);
        //拿到该新闻的所有评论  并添加到
        List<Comment> comments = getcomment(newsInfo.getId());
        newsInfo.setComments(comments);
        return Result.success("",newsInfo);
    }

//拿到该新闻的所有的评论
    @Override
    public List<Comment> getcomment(Long newInfo_id) {

        return dao.getList(newInfo_id);
    }


    @Override
    public Result save(User user, String text, Long id) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setCommentDetails(text);
        comment.setNewInfo_id(id);
        comment.setCreated(new Date());
        comment.setUpdated(new Date());

        try {
            dao.save(comment);
            return Result.success("评论成功！","");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }


//拿到该新闻小编的所有新闻
    public List<NewsInfo> getAllnews(Long id) {
        List<NewsInfo> allnews = dao.getAllnews(id);

        Collections.sort(allnews, new Comparator<NewsInfo>() {
            @Override
            public int compare(NewsInfo o1, NewsInfo o2) {
                try {
                    long time1 = o1.getUpdated().getTime();
                    long time2 = o2.getUpdated().getTime();
                    if (time1 < time2) {
                        return 1;
                    } else if (time1 > time2) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        return allnews;
    }

    /**
     * 根据传入新闻类型的ID 获取配置文件中写好的大新闻
     * @param id
     * @return
     */
    @Override
    public List<NewsInfo> getBigNewsByType(Integer id) {
        List<NewsInfo> list = dao.getBigNewsByTypeId(id);
        Collections.sort(list, new Comparator<NewsInfo>() {
            @Override
            public int compare(NewsInfo o1, NewsInfo o2) {
                try {
                    long time1 = o1.getUpdated().getTime();
                    long time2 = o2.getUpdated().getTime();
                    if (time1 < time2) {
                        return 1;
                    } else if (time1 > time2) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return list;
    }

    @Override
    public List<NewsInfo> getMinNewsByType(Integer id) {
        List<NewsInfo> list = dao.getMinNewsByType(id);
        if (list.size() < 1){
            return null;
        }
        Collections.sort(list, new Comparator<NewsInfo>() {
            @Override
            public int compare(NewsInfo o1, NewsInfo o2) {
                try {
                    long time1 = o1.getUpdated().getTime();
                    long time2 = o2.getUpdated().getTime();
                    if (time1 < time2) {
                        return 1;
                    } else if (time1 > time2) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return list;
    }


    /**
     *修改新闻阅读数量
     *
     */
    @Override
    public void upredcount(NewsInfo newsInfo) {
        dao.upredcount(newsInfo);
    }


    /**
     *                 修改点赞数量
     * @param commeID
     * @param likes
     * @return
     */
    @Override
    public void updateCommeIdNumber(Long commeID, Long likes) {
         dao.updateCommeIdNumber(commeID,likes);
    }

    /**
     * 搜索
     * @param seek
     * @return
     */
    @Override
    public List<NewsInfo> seeknews(String seek) {
        List<NewsInfo> list=dao.seeknews(seek);
        return list;
    }

}
