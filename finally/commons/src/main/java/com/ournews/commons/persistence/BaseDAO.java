package com.ournews.commons.persistence;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T extends BaseEntity> {
    //DAO增删改模板

    /**
     * 保存
     * @param t 保存的对象
     *
     */
    void save(T t);
    /**
     * 删除
     * @param t
     *
     */
    void delete(T t);

    /**
     * 修改/更新
     * @param t
     * @return
     */
    void update(T t);

    /**
     * 根据ID获取对象
     * @param id
     *
     */
    T get(@Param("id") Long id);



    /**
     *获取全部数据
     * @return
     */
    List<T> getList();


    /**
     * 获取当前表的数据总条数
     * @return
     */
    Integer getCount(T t);

}
