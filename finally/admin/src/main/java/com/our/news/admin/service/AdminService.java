package com.our.news.admin.service;

import com.ournews.commons.dto.BaseResult;
import com.ournews.domain.entity.AdminUser;
import com.ournews.domain.entity.User;

import java.util.List;

/**
 * @author ljs
 * @time 2018-10-29
 */
public interface AdminService {
    /**
     * 管理员登陆
     * @author ljs
     */
    AdminUser adminLogin(String adminName , String password);
    /**
      * 获取用户列表
      * @author ljs
      */
    List<AdminUser> getList();
}
