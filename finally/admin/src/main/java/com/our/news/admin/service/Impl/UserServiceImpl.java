package com.our.news.admin.service.Impl;

import com.our.news.admin.dao.UserDao;
import com.our.news.admin.service.UserService;
import com.ournews.commons.dto.BaseResult;
import com.ournews.commons.dto.PageInfo;
import com.ournews.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户ServiceImpl
 * @author ljs
 * @time 2018-10-29
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public User UserLogin(String email, String password) {
        return null;
    }

    @Override
    public void save(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUser(Long id) {

        return userDao.getUserById(id);
    }

    /**
      * 数据分页(包含查询分页、基本数据分页)
      * @author ljs
      */
    @Override
    public PageInfo<User> page(int start, int length, int draw, User user) {
//        userDao.update();
        PageInfo<User> userPageInfo = new PageInfo<>();
        userPageInfo.setDraw(draw);
        List<User> userList = null;
        Long count = 0L;
        if(user.getEmail() != null || user.getPhone() != null || user.getUsername() != null){
            userList = userDao.searchUserList(start, length,user.getUsername(),user.getPhone(),user.getEmail());
            count = userDao.searchUserCount(user);
        }else{
            userList = userDao.getUserList(start, length);
            count = userDao.getUserCount();
        }
        userPageInfo.setRecordsTotal(count.intValue());
        userPageInfo.setRecordsFiltered(count.intValue());
        userPageInfo.setData(userList);
        return userPageInfo;
    }

    /**
      * 冻结以及解冻
      * @author ljs
      */
    @Override
    public BaseResult status(String id,String isdelete) {
        BaseResult baseResult = BaseResult.success();
        if(isdelete.equals("1")){
            try{
                userDao.freeze(Long.parseLong(id));
                baseResult = BaseResult.success("冻结成功");
            }catch (Exception e){
                baseResult = BaseResult.fail("冻结失败");
            }
        }else{
            try{
                userDao.unfreeze(Long.parseLong(id));
                baseResult = BaseResult.success("解冻成功");
            }catch (Exception e){
                baseResult = BaseResult.fail("解冻失败");
            }

        }
        return baseResult;
    }

    /**
      * 删除单个用户
      * @author ljs
      */
    @Override
    public BaseResult delete(String id) {
        BaseResult baseResult = BaseResult.success();
        try{
            userDao.delById(Long.parseLong(id));
            baseResult = BaseResult.success("删除成功");
        } catch(Exception e){
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    /**
      * 批量删除用户
      * @author ljs
      */
    @Override
    public BaseResult deleteMulti(String ids) {
        BaseResult baseResult = BaseResult.success();
        try{
            String[] arrayId = ids.split(",");
            userDao.deleteMulti(arrayId);
            baseResult = BaseResult.success("删除成功");
        } catch (Exception e){
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    @Override
    public List<User> getList() {
        return userDao.getList();
    }

}
