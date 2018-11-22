package com.ournews.commons.persistence;


public interface BaseService < T extends BaseEntity> {
    //Service 模板  T为需要操作的实体类对象

    /**
     * 保存
     * @param t 保存的对象
     *
     */
    Result save(T t);
    /**
     * 删除
     * @param t
     *
     */
    Result delete(T t);

    /**
     * 修改/更新
     * @param t
     * @return
     */
    Result update(T t);

    /**
     * 根据ID获取对象
     * @param ID
     *
     */
    Result get(Long ID);


}
