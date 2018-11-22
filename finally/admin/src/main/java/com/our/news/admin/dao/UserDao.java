package com.our.news.admin.dao;

import com.ournews.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ljs
 * @time 2018-10-29
 */
@Repository
public interface UserDao {

    User getUserById(Long id);

    List<User> getUserList(@Param("start") int start,@Param("length") int length);

    Long getUserCount();

    List<User> searchUserList(@Param("start") int start,@Param("length") int length,@Param("username") String username,@Param("phone") String phone,@Param("email") String email);

    Long searchUserCount(User user);

    void freeze(Long id);

    void unfreeze(Long id);

    void delById(Long id);

    void addUser(User user);

    void deleteMulti(String[] ids);

    List<User> getList();

    void update();

}