package com.ournews.commons.persistence;


import lombok.Getter;
import lombok.Setter;

//结果集
public class Result {
    public static final Integer ERROR_STATUS = 500;//失败的状态
    public static final Integer SUCCESS = 200;//成功的状态

    @Getter
    @Setter
    private Integer status;//当前状态
    @Getter
    @Setter
    private String message;//返回的信息
    @Getter
    @Setter
    private Object data;//返回的对象或者值

    public static Result success(String message, Object obj) {
        Result result = new Result(message);
        result.setStatus(Result.SUCCESS);
        result.setData(obj);
        return result;
    }
    public static Result error(String message) {
        Result result = new Result(message);
        result.setStatus(Result.ERROR_STATUS);
        return result;
    }

    public Result() {
    }

    public Result(String message) {
        this.message = message;
    }


}
