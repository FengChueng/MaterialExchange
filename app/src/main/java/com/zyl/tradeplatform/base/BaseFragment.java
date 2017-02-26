package com.zyl.tradeplatform.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangyinglong on 2017/2/21.
 */
public abstract class BaseFragment extends Fragment{
    /*根view*/
    private View mRootView;
    /*是否对用户可见*/
    protected boolean mIsVisible;
    /*是否加载完成,当执行完onCreateView()即为true*/
    protected boolean mIsPrepare;

    /**
     * 得到当前Fragment的实例
     * @param clz
     * @param bundle    传递的参数
     * @return
     */
    public Fragment newInstance(Class clz,Bundle bundle){
        Fragment instance = null;
        try {
            instance = (Fragment) clz.newInstance();
            instance.setArguments(bundle);
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView != null)
            mRootView = inflater.inflate(getLayoutId(),null);
        initView();
        mIsPrepare = true;
        onLazyLoad();
        return mRootView;
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();



    /**
     * 得到布局文件id
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 懒加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.mIsVisible = isVisibleToUser;
        if (mIsVisible) {
            onVisibleToUser();
        }
    }

    /**
     * 对用户可见时加载数据
     */
    protected void onVisibleToUser(){
        if (mIsPrepare&&mIsVisible) {
            onLazyLoad();
        }
    }

    /**
     * 加载数据
     */
    protected abstract void onLazyLoad();



}
