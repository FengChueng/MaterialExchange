package com.zyl.tradeplatform.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.zyl.tradeplatform.R;
import com.zyl.tradeplatform.widget.slideback.SlideBackActivity;

import butterknife.ButterKnife;

/**
 * Created by zhangyinglong on 2017/2/21.
 * 支持滑动左侧退出Activity
 * extends SlideBackActivity
 * setSlideable(true);//允许滑动左侧退出activity(默认)
 *
 * 支持自动适配
 * SlideBackActivity extends AutoLayoutActivity
 */
public abstract class BaseActivity extends SlideBackActivity {
    /* 日志标志 */
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity-->onCreate()");
        Bundle bundle = getIntent().getExtras();
        initParams(bundle);
        setContentView(getContentView());
        initView(savedInstanceState);
        ButterKnife.bind(this);
    }

    protected View getContentView() {
        View contentView = null;
        if (getLayoutID() != 0) {
            contentView = LayoutInflater.from(this).inflate(getLayoutID(), null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT);
            contentView.setLayoutParams(params);
        }
        return contentView;
    }

    /**
     * 初始化参数
     *
     * @param params 传递参数
     */
    protected abstract void initParams(Bundle params);

    /**
     * 初始化控件,处理状态保存
     *
     * @param savedInstanceState
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * 得到布局id
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutID();


    /**
     * 沉浸状态栏
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 初始化toolbar
     * @param title 标题
     * @return
     */
    public Toolbar initToolBar(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBarTitle(title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return toolbar;
    }

    /**
     * 设置toolbar标题
     * @param title     标题
     */
    public void setToolBarTitle(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView)toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
    }

    /**
     * 没有返回按钮
     * @param title
     * @return
     */
    public Toolbar initToolBarNoBack(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView)toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}