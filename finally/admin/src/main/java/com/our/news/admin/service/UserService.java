package com.our.news.admin.service;

import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.AdminUser;
import com.ournews.domain.entity.User;

import java.util.List;

/**
 * @author ljs
 * @time 2018-10-29
 */
public interface UserService {
    User UserLogin(String email,String password);

    void save(User user);
    User getUser(Long id);


    PageInfo<User> page(int start,int length,int draw,User user);
    BaseResult status(String id,String isdelete);
    BaseResult delete(String id);
    BaseResult deleteMulti(String ids);
    List<User> getList();
}
