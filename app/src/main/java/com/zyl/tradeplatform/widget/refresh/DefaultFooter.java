package com.zyl.tradeplatform.widget.refresh;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.utils.UiUtils;


/**
 * Created by Anthony on 2016/7/18.
 */
public class DefaultFooter extends BaseIndicator {
    private TextView mStringIndicator;
    private ProgressWheel progress_wheell;
    private int default_rim_color;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.layout_refresh_footer_view, parent, true);
        View child = v.getChildAt(v.getChildCount() - 1);
        mStringIndicator = (TextView) child.findViewById(R.id.tv_footer);
        progress_wheell = (ProgressWheel) v.findViewById(R.id.progress_wheell);
        default_rim_color = progress_wheell.getRimColor();
        return child;
    }

    @Override
    public void onAction() {
        mStringIndicator.setText(UiUtils.getString(R.string.refresh_release_loading_more));
    }

    @Override
    public void onUnaction() {
        mStringIndicator.setText(UiUtils.getString(R.string.refresh_pull_up));
    }

    @Override
    public void onRestore() {
        mStringIndicator.setText(UiUtils.getString(R.string.refresh_pull_up));
        progress_wheell.setRimColor(default_rim_color);
        progress_wheell.stopSpinning();
    }

    @Override
    public void onLoading() {
        mStringIndicator.setText(UiUtils.getString(R.string.refresh_loading));
        progress_wheell.setRimColor(Color.parseColor("#00000000"));
        progress_wheell.spin();
    }
}
