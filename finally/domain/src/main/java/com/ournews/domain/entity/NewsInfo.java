package com.ournews.domain.entity;



import com.ournews.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 新闻
 * @author ljs
 * @time 2018-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NewsInfo extends BaseEntity {
    private String name;
    private User user;
    private String detail;
    private List<Comment> comments;
    //阅读总量
    private int readCount;
    //评论总量
    private int commentCount;
    private String type;
    private String picture;
}
