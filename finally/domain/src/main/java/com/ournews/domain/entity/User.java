package com.ournews.domain.entity;


import com.ournews.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ljs
 * @time 2018-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String picture;

    /**
     * 0 表示永久性拉黑
     *
     * 1 表示可用
     *
     * 2 表示冻结
     */
    private int isdelete;
}
