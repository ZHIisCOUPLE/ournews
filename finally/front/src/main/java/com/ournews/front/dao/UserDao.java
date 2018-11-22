package com.ournews.front.dao;

import com.ournews.commons.persistence.BaseDAO;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDAO<User> {
    /**
     * 用邮箱和密码查找用户
     * @param email
     * @param password
     * @return
     */
    User getEmailAndPassword(@Param("email") String email, @Param("password") String password);

    /**
     * 查找是否有邮箱
     * @param email
     * @return
     */
    int getEmail(@Param("email") String email);

    /**
     * 此手机号是否注册过
     * @param phone
     * @return
     */
    int getPhone(@Param("phone") String phone);

    /**
     * 用户名是否重复
     * @param username
     * @return
     */
    int getUserName(@Param("username") String username);

     List<NewsInfo> getUserNewsList(@Param("id") Long id);

}
