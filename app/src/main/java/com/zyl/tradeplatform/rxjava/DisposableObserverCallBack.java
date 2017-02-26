package com.zyl.tradeplatform.rxjava;

import com.zyl.tradeplatform.bean.ResponseResult;
import com.zyl.tradeplatform.constants.ApiConstant;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator on 2017/2/26.
 */

public class DisposableObserverCallBack<T> extends DisposableObserver<T>{
    private ApiCallBack<T> apiCallBack;

    public DisposableObserverCallBack(ApiCallBack<T> apiCallBack){
        this.apiCallBack = apiCallBack;
    }

    @Override
    public void onNext(T value) {
        if(value instanceof ResponseResult){
            ResponseResult result = (ResponseResult) value;
            if (result.status == ApiConstant.REQUEST_SUCCESS) {
                apiCallBack.onSuccess(value);
            }
        }

    }

    @Override
    public void onError(Throwable e) {
        apiCallBack.onError();
    }

    @Override
    public void onComplete() {
        apiCallBack.onComplete();
    }
}
