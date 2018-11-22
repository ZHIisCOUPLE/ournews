package com.our.news.admin.dao;

import com.ournews.domain.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ljs
 * @time 2018-10-31
 */
@Repository
public interface CommentDao {
    List<Comment> getCommentListByNewInfoId(@Param("start") int start, @Param("length") int length, @Param("id") Long id);

    Long getCommentCountByNewInfoId(Long id);

    void deleteCommentById(Long id);

    void deleteCommentByNewInfoId(Long id);

    String getComment(Long id);
}
