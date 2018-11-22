package com.our.news.admin.service.Impl;

import com.our.news.admin.dao.AdminDao;
import com.our.news.admin.service.AdminService;
import com.ournews.domain.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @author ljs
 * @time 2018-10-29
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
      * 管理员登陆
      * @author ljs
      */
    @Override
    public AdminUser adminLogin(String adminName, String password) {
        AdminUser admin = adminDao.getAdminUserByadminname(adminName);
        if(admin != null){
//            String passwordmd5 = DigestUtils.md5DigestAsHex(password.getBytes());
            if(password.equals(admin.getPassword())){
                return admin;
            }
        }
        return null;
    }

    @Override
    public List<AdminUser> getList() {
        return adminDao.getList();
    }
}
