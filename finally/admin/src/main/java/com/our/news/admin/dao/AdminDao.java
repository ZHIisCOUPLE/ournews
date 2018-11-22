package com.our.news.admin.dao;

import com.ournews.domain.entity.AdminUser;
import com.ournews.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ljs
 * @time 2018-10-29
 */
@Repository
public interface AdminDao {
    /**
      * 获取单个管理员的信息
      * @author hh
      */
    AdminUser getAdminUserByadminname(String adminname);
    List<AdminUser> getList();
}
