package com.ournews.commons.persistence;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public abstract class BaseServiceImpl<T extends BaseEntity, D extends BaseDAO> implements BaseService<T> {

    @Autowired
    protected D dao;

    @Override
    public Result save(T t) {
        try {
            t.setCreated(new Date());
            t.setUpdated(new Date());
            dao.save(t);
            return Result.success("保存成功！", t);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result delete(T t) {
        try {
            dao.delete(t);
            return Result.success("删除成功！", t);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result update(T t) {
        try {

            t.setUpdated(new Date());
            dao.update(t);
            return Result.success("", t);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result get(Long id) {
        try {
            T t = (T) dao.get(id);
            return Result.success("", t);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }





}
