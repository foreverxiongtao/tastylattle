package com.desperado.tastylattlelib.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.desperado.customerlib.view.autolayout.AutoRelativeLayout;
import com.desperado.tastylattlelib.R;
import com.desperado.tastylattlelib.config.LocalConstant;
import com.desperado.tastylattlelib.utils.AbViewUtil;
import com.jaeger.library.StatusBarUtil;

import me.yokeyword.fragmentation.SupportActivity;


/*
 *
 *
 * 版 权 :@Copyright 北京*****有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/4/6 下午17:01
 *
 * 描 述 :toolbar基类
 *
 * 修订日期 :
 */
public class ToolBarActivity extends SupportActivity {
    /**
     * 左边返回布局
     */
    private LinearLayout ll_title_bar_left_click;
    /****
     * 加载容器
     */
    private LinearLayout ll_loadding_activity_state_loading;
    /****
     * 加载图标
     */
    private ImageView iv_loadding_activity_state_loading_icon;
    /****
     * 加载文字描述
     */
    private TextView tv_loadding_activity_state_loading_text;
    /***
     * 没有数据容器
     */
    private LinearLayout ll_loadding_activity_state_nodata;
    /***
     * 没有数据图片
     */
    private ImageView iv_loadding_activity_state_nodata_icon;
    /***
     * 没有数据文本描述
     */
    private TextView tv_loadding_activity_state_nodata_text;
    /***
     * 无网络图片容器
     */
    private LinearLayout ll_loadding_activity_state_network_error;
    /***
     * 无网络图片
     */
    private ImageView iv_loadding_activity_state_network_error_icon;
    /***
     * 无网络描述文本
     */
    private TextView tv_loadding_activity_state_network_error_text;
    /***
     * 重试按钮
     */
    private Button btn_loadding_activity_state_network_error_retry;
    /***
     * 标题栏容器
     */
    private RelativeLayout rl_title_bar;
    /***
     * 标题左边图标
     */
    private ImageView iv_title_bar_left_icon;
    /***
     * 标题左边文本
     */
    private TextView tv_title_bar_left_text;
    /***
     * 标题中间的标题名称
     */
    private TextView tv_title_bar_center_title;
    /***
     * 标题右边点击View
     */
    private LinearLayout ll_title_bar_right_click;
    /***
     * 标题右边图标
     */
    private ImageView iv_title_bar_right_icon;
    /***
     * 标题右边文本
     */
    private TextView tv_title_bar_right_text;
    /***
     * 中间标题的图片
     */
    private ImageView iv_title_bar_center_title;

    /***
     * activity的状态视图
     ***/
    protected View activityStateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View _mUserView) {
        RelativeLayout linear = new RelativeLayout(this);
        View title = LayoutInflater.from(this).inflate(R.layout.toolbar, null);
        title.setId(R.id.id_app_title);
        linear.addView(title, 0);
        AutoRelativeLayout.LayoutParams params = new AutoRelativeLayout.LayoutParams(AutoRelativeLayout.LayoutParams.MATCH_PARENT, AutoRelativeLayout.LayoutParams.MATCH_PARENT);
        params.addRule(AutoRelativeLayout.BELOW, title.getId());
        linear.addView(_mUserView, params);
        //加载进度view
        AutoRelativeLayout.LayoutParams activityStateView_params = new AutoRelativeLayout.LayoutParams(AutoRelativeLayout.LayoutParams.MATCH_PARENT, AutoRelativeLayout.LayoutParams.MATCH_PARENT);
        activityStateView_params.addRule(AutoRelativeLayout.CENTER_IN_PARENT);
        activityStateView = LayoutInflater.from(this).inflate(R.layout.loading_activity_state, null);
        findLoadingStateViewById();
        linear.addView(activityStateView, activityStateView_params);
        super.setContentView(linear);
        findTitleViewById(title);
        setTitleRightViewShow(false);
        /*默认设置标题栏为白色*/
//        rl_title_bar.setBackgroundResource(R.color.green);
        setTitleBarBg(R.color.white);
        //后退键
        ll_title_bar_left_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
//                finish();
            }
        });
    }

    /**
     * ACTIVITY_STATE_LOADING 正在加载状态
     * ACTIVITY_STATE_NODATA 没有数据状态
     * ACTIVITY_STATE_NETWORK_ERROR 网络异常状态
     *
     * @param activityState 状态
     * @param text          没有数据显示文案
     */
    public void setActivityLoadingState(int activityState, String text) {
        activityStateView.setVisibility(View.VISIBLE);
        if (null != activityStateView) {
            if (activityState == LocalConstant.ACTIVITY_STATE_LOADING) {
                if (ll_loadding_activity_state_network_error.getVisibility() == View.VISIBLE) {
                    ll_loadding_activity_state_network_error.setVisibility(View.GONE);
                }
                ll_loadding_activity_state_loading.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(text)) {
                    tv_loadding_activity_state_loading_text.setText(text);
                }
            } else if (activityState == LocalConstant.ACTIVITY_STATE_NODATA) {
                ll_loadding_activity_state_loading.setVisibility(View.GONE);
                ll_loadding_activity_state_nodata.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(text)) {
                    tv_loadding_activity_state_nodata_text.setText(text);
                }
            } else if (activityState == LocalConstant.ACTIVITY_STATE_NETWORK_ERROR) {
                ll_loadding_activity_state_loading.setVisibility(View.GONE);
                ll_loadding_activity_state_network_error.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(text)) {
                    tv_loadding_activity_state_network_error_text.setText(text);
                }
            } else {
                ll_loadding_activity_state_loading.setVisibility(View.GONE);
                activityStateView.setVisibility(View.GONE);

            }
        }
    }

    public View getActivityStateView() {
        return btn_loadding_activity_state_network_error_retry;
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.light_title_grey));
    }


    private void findTitleViewById(View title) {
//        toolbar = (Toolbar) title.findViewById(R.id.toolbar);
        rl_title_bar = (RelativeLayout) title.findViewById(R.id.rl_title_bar);
        //标题左边点击View
        ll_title_bar_left_click = (LinearLayout) title.findViewById(R.id.ll_title_bar_left_click);
        //标题左边图标
        iv_title_bar_left_icon = (ImageView) title.findViewById(R.id.iv_title_bar_left_icon);
        //标题左边文本
        tv_title_bar_left_text = (TextView) title.findViewById(R.id.tv_title_bar_left_text);
        //标题中间的标题名称
        tv_title_bar_center_title = (TextView) title.findViewById(R.id.tv_title_bar_center_title);
        //标题右边点击View
        ll_title_bar_right_click = (LinearLayout) title.findViewById(R.id.ll_title_bar_right_click);
        //标题右边图标
        iv_title_bar_right_icon = (ImageView) title.findViewById(R.id.iv_title_bar_right_icon);
        //标题右边文本
        tv_title_bar_right_text = (TextView) title.findViewById(R.id.tv_title_bar_right_text);
        //中间标题的图片
        iv_title_bar_center_title = (ImageView) title.findViewById(R.id.iv_title_bar_center_title);
    }

    private void findLoadingStateViewById() {
        //加载数据
        ll_loadding_activity_state_loading = (LinearLayout) activityStateView.findViewById(R.id.ll_loadding_activity_state_loading);
        iv_loadding_activity_state_loading_icon = (ImageView) activityStateView.findViewById(R.id.iv_loadding_activity_state_loading_icon);
        tv_loadding_activity_state_loading_text = (TextView) activityStateView.findViewById(R.id.tv_loadding_activity_state_loading_text);
        //没有数据
        ll_loadding_activity_state_nodata = (LinearLayout) activityStateView.findViewById(R.id.ll_loadding_activity_state_nodata);
        iv_loadding_activity_state_nodata_icon = (ImageView) activityStateView.findViewById(R.id.iv_loadding_activity_state_nodata_icon);
        tv_loadding_activity_state_nodata_text = (TextView) activityStateView.findViewById(R.id.tv_loadding_activity_state_nodata_text);
        //网络异常
        ll_loadding_activity_state_network_error = (LinearLayout) activityStateView.findViewById(R.id.ll_loadding_activity_state_network_error);
        iv_loadding_activity_state_network_error_icon = (ImageView) activityStateView.findViewById(R.id.iv_loadding_activity_state_network_error_icon);
        tv_loadding_activity_state_network_error_text = (TextView) activityStateView.findViewById(R.id.tv_loadding_activity_state_network_error_text);
        btn_loadding_activity_state_network_error_retry = (Button) activityStateView.findViewById(R.id.btn_loadding_activity_state_network_error_retry);
        activityStateView.setVisibility(View.GONE);
    }

    @Override
    public void setContentView(int layoutResID) {
        View _mUserView = LayoutInflater.from(this).inflate(layoutResID, null, false);
        setContentView(_mUserView);
    }

    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideTitle() {
        if (rl_title_bar != null) {
            rl_title_bar.setVisibility(View.GONE);
        }
    }

    public void hidelefticon() {
        iv_title_bar_left_icon.setVisibility(View.GONE);
    }

    public TextView getTv_title_bar_right_text() {
        return tv_title_bar_right_text;
    }

    /**
     * 设置中间标题的图片
     *
     * @param resId
     */
    public void setCenterTitleImage(int resId) {
        iv_title_bar_center_title.setImageResource(resId);
    }

    /**
     * 设置中间标题的图片是否可见
     *
     * @param boo
     */
    public void setCenterTitleImageShow(boolean boo) {
        if (boo) {
            iv_title_bar_center_title.setVisibility(View.VISIBLE);
        } else {
            iv_title_bar_center_title.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏背景颜色
     *
     * @param color
     */
    public void setTitleBarBg(int color) {
        rl_title_bar.setBackgroundResource(color);
    }

    /**
     * 设置标题栏左边view背景
     *
     * @param resid 资源文件id
     */
    public void setTitleLeftViewBg(int resid) {
        ll_title_bar_left_click.setBackgroundResource(resid);
    }

    /**
     * 设置标题栏左边图标
     *
     * @param resid 资源文件id
     */
    public void setTitleLeftIcon(int resid) {
        iv_title_bar_left_icon.setBackgroundResource(resid);
    }

    /**
     * 设置标题栏左边图标
     *
     * @param resid  资源文件id
     * @param width  宽
     * @param height 高
     */
    public void setTitleLeftIcon(int resid, int width, int height) {
        iv_title_bar_left_icon.setBackgroundResource(resid);
        AbViewUtil.setViewWH(iv_title_bar_left_icon, width, height);
    }

    /**
     * 设置标题栏左边text
     *
     * @param text
     */
    public void setTitleLeftText(String text) {
        tv_title_bar_left_text.setText(text);
    }

    /**
     * 设置标题栏左边text颜色
     *
     * @param color
     */
    public void setTitleLeftTextColor(int color) {
        tv_title_bar_left_text.setTextColor(Color.parseColor(getString(color)));
    }

    /**
     * 设置标题栏名称
     *
     * @param title 标题名称
     */
    public void setTitle(String title) {
        tv_title_bar_center_title.setText(title);
    }

    /**
     * 设置标题栏名称
     *
     * @param title 标题名称
     */
    public void setTitle(int title) {
        tv_title_bar_center_title.setText(getString(title));
    }

    /**
     * 设置标题栏名称颜色
     *
     * @param color
     */
    public void setTitleColor(int color) {
        tv_title_bar_center_title.setTextColor(Color.parseColor(getString(color)));
    }

    /**
     * 设置标题栏右边view背景
     *
     * @param resid 资源文件id
     */
    public void setTitleRightViewBg(int resid) {
        ll_title_bar_right_click.setBackgroundResource(resid);
    }

    /**
     * 设置标题栏右边图标
     *
     * @param resid 资源文件id
     */
    public void setTitleRightIcon(int resid) {
        iv_title_bar_right_icon.setBackgroundResource(resid);
    }

    /**
     * 设置标题栏右边图标
     *
     * @param resid  资源文件id
     * @param width  宽
     * @param height 高
     */
    public void setTitleRightIcon(int resid, int width, int height) {
        iv_title_bar_right_icon.setBackgroundResource(resid);
        AbViewUtil.setViewWH(iv_title_bar_left_icon, width, height);
    }

    /**
     * 设置标题栏右边text
     *
     * @param text
     */
    public void setTitleRightText(String text) {
        if (ll_title_bar_right_click.getVisibility() == View.GONE || ll_title_bar_right_click.getVisibility() == View.INVISIBLE) {
            ll_title_bar_right_click.setVisibility(View.VISIBLE);
        }
        tv_title_bar_right_text.setText(text);
    }

    /**
     * 设置标题栏右边text颜色
     *
     * @param color
     */
    public void setTitleRightTextColor(int color) {
        tv_title_bar_right_text.setTextColor(Color.parseColor(getString(color)));
    }

    /**
     * 是否隐藏或者显示标题栏左边view
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleLeftViewShow(boolean boo) {
        if (boo) {
            ll_title_bar_left_click.setVisibility(View.VISIBLE);
        } else {
            ll_title_bar_left_click.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 是否隐藏或者显示标题栏左边文本
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleLeftTextShow(boolean boo) {
        if (boo) {
            tv_title_bar_left_text.setVisibility(View.VISIBLE);
        } else {
            tv_title_bar_left_text.setVisibility(View.GONE);
        }
    }

    /**
     * 是否隐藏或者显示标题栏右边view
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleRightViewShow(boolean boo) {
        if (boo) {
            ll_title_bar_right_click.setVisibility(View.VISIBLE);
        } else {
            ll_title_bar_right_click.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 是否隐藏或者显示标题栏右边图标
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleRightIconShow(boolean boo) {
        if (boo) {
            iv_title_bar_right_icon.setVisibility(View.VISIBLE);
        } else {
            iv_title_bar_right_icon.setVisibility(View.GONE);
        }
    }

    /**
     * 是否隐藏或者显示标题栏右边文本
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleRightTextShow(boolean boo) {
        if (boo) {
            tv_title_bar_right_text.setVisibility(View.VISIBLE);
        } else {
            tv_title_bar_right_text.setVisibility(View.GONE);
        }
    }

    /**
     * 标题栏左边view事件
     *
     * @return
     */
    public LinearLayout getTitleLeftClick() {
        return ll_title_bar_left_click;
    }

    /**
     * 标题栏右边view事件
     *
     * @return
     */
    public LinearLayout getTitleRightClick() {
        return ll_title_bar_right_click;
    }
}
