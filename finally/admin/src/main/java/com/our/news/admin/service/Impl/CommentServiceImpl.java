package com.our.news.admin.service.Impl;

import com.our.news.admin.dao.CommentDao;
import com.our.news.admin.dao.NewInfoDao;
import com.our.news.admin.service.CommentService;
import com.our.news.admin.service.NewInfoService;
import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.Comment;
import com.ournews.domain.entity.NewsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ljs
 * @time 2018-10-31
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private NewInfoDao newInfoDao;

    /**
     * 评论分页(只根据新闻id查询的数据分页)
     * @author ljs
     */
    @Override
    public PageInfo<Comment> page(int start, int length, int draw, Comment comment) {
        PageInfo<Comment> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setData(commentDao.getCommentListByNewInfoId(start,length,comment.getNewInfo_id()));
        Long count = commentDao.getCommentCountByNewInfoId(comment.getNewInfo_id());
        pageInfo.setRecordsTotal(count.intValue());
        pageInfo.setRecordsFiltered(count.intValue());
        return pageInfo;
    }

    /**
     * 删除单个评论
     * @author ljs
     */
    @Override
    public BaseResult deleteComment(String id) {
        BaseResult baseResult = null;
        try{
            Long newId = Long.parseLong(commentDao.getComment(Long.parseLong(id)));
            commentDao.deleteCommentById(Long.parseLong(id));
            Long count = commentDao.getCommentCountByNewInfoId(newId);
            newInfoDao.updateNewInfoCommentCount(""+newId,count.intValue());
            baseResult = BaseResult.success("删除成功");
        } catch(Exception e){
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

}
