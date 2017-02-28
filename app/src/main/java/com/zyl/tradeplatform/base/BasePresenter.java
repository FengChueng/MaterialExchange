package com.zyl.tradeplatform.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhangyinglong on 2017/2/21.
 */
public abstract class BasePresenter<T extends BaseView> {
    public T mView;
    private CompositeDisposable mCompositeDisposable;
    public BasePresenter(){}
    public BasePresenter(T view){
        //view不能为null
        if (view == null) {
            throw new NullPointerException("view can't be null");
        }
        this.mView = view;
    }

    public void addSubscription(Disposable disposable){
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);

    }

    public void detach(){
        mView = null;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
