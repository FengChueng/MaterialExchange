package com.zyl.tradeplatform.rxjava;

/**
 * Created by Administrator on 2017/2/26.
 */

public interface ApiCallBack<T>{
    void onSuccess(T model);
    void onError();
    void onComplete();
}
