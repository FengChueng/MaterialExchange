package com.zyl.tradeplatform.base;

import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by Administrator on 2017/2/26.
 */

public abstract class MvpBaseActivity<T extends BasePresenter> extends BaseActivity{
    protected T mPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState, persistentState);
    }

    /**
     * 创建presenter
     * @return
     */
    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        //防止内存泄漏,取消与view的关联
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }
}
