package com.ournews.front.service.impl;

import com.ournews.commons.persistence.BaseServiceImpl;
import com.ournews.commons.persistence.Result;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import com.ournews.front.dao.UserDao;
import com.ournews.front.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDao> implements UserService {
    /**
     * 用邮箱和密码查找用户
     * @param email
     * @param password
     * @return
     */
    @Override
    public Result getEmailAndPassword(String email, String password) {

    //邮箱密码是否为空？

        if (email!=null && password != null){

            //对密码进行加密
//            password = DigestUtils.md5DigestAsHex(password.getBytes());

            User user = dao.getEmailAndPassword(email, password);

            if (user!=null){
                //为0则被拉黑
                if (user.getIsdelete()==0){
                    return Result.error("该账号异常，请重新注册一个新的账号或登录别的账号。");
                }

                //为2则冻结
                if (user.getIsdelete()==2){
                    return Result.error("您的账号已被冻结，请尽快与管理员联系，否则充钱也没有！   才怪！！！！！");
                }

                return Result.success("",user);

            }else {
                return Result.error("邮箱或密码错误！");
            }
        }else {
            return Result.error("邮箱和密码不能为空！");
        }

    }

    @Override
    public Result RegisterAjax(User user) {
        int email = dao.getEmail(user.getEmail());
        if(email>0){
            return Result.error("该邮箱已被使用");
        }
        int phone = dao.getPhone(user.getPhone());
        if(phone>0){
            return Result.error("该手机已注册");
        }
        int userName = dao.getUserName(user.getUsername());
        if(userName>0){
            return Result.error("该用户名已存在");
        }
        return Result.success("1","message");
    }


    /**
     * 加密密码
     * @param user
     * @return
     */
    /*@Override
    public Result save(User user) {
        String password = user.getPassword();
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        return super.save(user);
    }*/

    /**
     * 设置更新时间
     * @param user
     * @return
     */
    @Override
    public Result update(User user) {
        user.setUpdated(new Date());
        return super.update(user);
    }

    @Override
    public List<NewsInfo> getUserNewsList(Long id) {
        List<NewsInfo> getUserNewsList = dao.getUserNewsList(id);
        return getUserNewsList;
    }
}
