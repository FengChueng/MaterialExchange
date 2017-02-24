package com.zyl.tradeplatform.bean;

/**
 * Created by zhangyinglong on 2017/2/21.
 */
public class ResponseResult<T> {
    /** 状态码 ApiConstant.REQUEST_SUCCESS/ApiConstant.REQUEST_FAIL */
    public int status;
    /** 返回信息*/
    public String msg;
    /** 返回数据*/
    public T data;

}
