package com.our.news.admin.service;

import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.Comment;

/**
 * @author ljs
 * @time 2018-10-29
 */
public interface CommentService {

    PageInfo<Comment> page(int start,int length, int draw,Comment comment);
    BaseResult deleteComment(String id);
}
