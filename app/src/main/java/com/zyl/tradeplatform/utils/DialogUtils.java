package com.zyl.tradeplatform.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.zyl.tradeplatform.R;


/**
 * Created by zhouyou on 2016/7/25.
 * Class desc:
 * <p/>
 * Dialog相关工具类，对Dialog进行二次封装
 */
public class DialogUtils {

    /**
     * 显示单选列表项Dialog
     *
     * @param context  上下文
     * @param items    Item数据
     * @param listener 回调监听
     */
    public static void showItems(Context context, String[] items, DialogInterface.OnClickListener listener) {
        showItems(context, items, true, listener);
    }

    /**
     * 显示单选列表项Dialog
     *
     * @param context    上下文
     * @param items      Item数据
     * @param cancelable 是否可以被取消
     * @param listener   回调监听
     */
    public static void showItems(Context context, String[] items, boolean cancelable, DialogInterface.OnClickListener listener) {
        showItems(context, null, items, cancelable, listener);
    }

    /**
     * 显示单选列表项Dialog
     *
     * @param context    上下文
     * @param title      标题
     * @param items      Item数据
     * @param listener   回调监听
     */
    public static void showItems(Context context, String title, String[] items, DialogInterface.OnClickListener listener) {
        showItems(context, title, items, true, listener);
    }


    /**
     * 显示单选列表项Dialog
     *
     * @param context    上下文
     * @param title      标题
     * @param items      Item数据
     * @param cancelable 是否可以被取消
     * @param listener   回调监听
     */
    public static void showItems(Context context, String title, String[] items, boolean cancelable, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setItems(items, listener)
                .setCancelable(cancelable);

        // 设置标题
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);

        // 构建Dialog
        builder.create()
                .show();
    }

    /**
     * 弹出提示对话框Dialog
     */
    public static void showDialog(Context context, String message, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(UiUtils.getString(R.string.dialog_title))
                .setMessage(message);

        builder.setPositiveButton(UiUtils.getString(R.string.dialog_affirm), listener)
                .create()
                .show();
    }

    /**
     * 弹出带标题提示对话框Dialog
     */
    public static void showDialog(Context context, String title, String message, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message);

        builder.setPositiveButton(UiUtils.getString(R.string.dialog_affirm), listener)
                .setNegativeButton(UiUtils.getString(R.string.dialog_cancel), null)
                .create()
                .show();
    }
}
