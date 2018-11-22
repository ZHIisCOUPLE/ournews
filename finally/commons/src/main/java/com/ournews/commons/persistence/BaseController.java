package com.ournews.commons.persistence;


import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<S extends BaseService, T extends BaseEntity> {
    //controller 操作模板
    @Autowired
    protected S service;

}
