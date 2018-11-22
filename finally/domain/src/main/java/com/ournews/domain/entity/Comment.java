package com.ournews.domain.entity;


import com.ournews.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评论
 * @author ljs
 * @time 2018-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseEntity {
    private long newInfo_id;
    private User user;
    private String commentDetails;
    private Long likes;
}
