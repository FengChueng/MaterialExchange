package com.zyl.tradeplatform.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by zhouyou on 2016/6/24.
 * Class desc:
 *
 * 软键盘封装工具类
 */
public class KeyBoardUtils {

    private KeyBoardUtils() {
        mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private static KeyBoardUtils instance;
    private InputMethodManager mInputMethodManager;
    private static Activity mActivity;

    public static KeyBoardUtils getInstance(Activity activity) {
        mActivity = activity;
        if (instance == null) {
            instance = new KeyBoardUtils();
        }
        return instance;
    }

    /**
     * 强制显示输入法
     */
    public void show() {
        show(mActivity.getWindow().getCurrentFocus());
    }

    public void show(View view) {
        mInputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 强制关闭输入法
     */
    public void hide() {
        hide(mActivity.getWindow().getCurrentFocus());
    }

    public void hide(View view) {
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 如果输入法已经显示，那么就隐藏它；如果输入法现在没显示，那么就显示它
     */
    public void showOrHide() {
        mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断软键盘是否弹起
     */
    public static boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }

    /**
     * 隐藏软键盘
     */
    public static void hideKeyboard(Context mContext, View v) {
        if(v != null && isKeyboardShown(v)){
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
