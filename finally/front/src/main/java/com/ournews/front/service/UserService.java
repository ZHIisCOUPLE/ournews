package com.ournews.front.service;

import com.ournews.commons.persistence.BaseService;
import com.ournews.commons.persistence.Result;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;

import java.util.List;


public interface UserService extends BaseService<User> {

    /**
     * 根据邮箱以及密码获取用户 / 登录检查
     * @param email
     * @param password
     * @return
     */
    Result getEmailAndPassword(String email, String password);

    /**
     * ajax 查询用户
     * @param user
     * @return
     */
    Result RegisterAjax(User user);

    List<NewsInfo> getUserNewsList(Long id);
}
