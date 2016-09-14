package com.desperado.tastylattlelib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.desperado.customerlib.view.autolayout.AutoRelativeLayout;
import com.desperado.flashdonkeyclient.R;
import com.desperado.flashdonkeyclient.listener.OnActivitySelectListenner;
import com.desperado.flashdonkeyclient.mvp.presenter.base.BasePresenter;


public abstract class BaseFragment extends Fragment {
    /**
     * Fragment title
     */
    public String fragmentTitle;
    /**
     * 是否可见状态
     */
    private boolean isVisible;
    /**
     * 标志位，View已经初始化完成。
     */
    public boolean isPrepared;
    /**
     * 是否第一次加载
     */
    public boolean isFirstLoad = true;
    /**
     * 用户在快速切换时，会取消当前加载数据的操作
     */
    protected boolean hasCancel;
    private View mFragmentView;
    protected long startTime;
    private long endTime;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected boolean mHasLoadedOnce;

    /**
     * 判断是否上拉下拉加载的状态    来自网络下载加载:TYPE_IS_FROM_NET,上拉加载:TYPE_IS_FROM_PULLUP
     */
    public int loadingStatus = 0;//


    //加载进度
    private LinearLayout ll_loadding_activity_state_loading;
    private LinearLayout ll_loadding_activity_state_nodata;
    private TextView tv_loadding_activity_state_nodata_text;
    private LinearLayout ll_loadding_activity_state_network_error;
    private TextView tv_loadding_activity_state_network_error_text;
    private Button btn_loadding_activity_state_network_error_retry;
    private TextView tv_loadding_activity_state_loading_text;
    private View loading_activity_state;
    private SVProgressHUD _loadingDialog;

    protected abstract BasePresenter getCurrentPersenter(); //获取当前的业务处理类

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 若 viewpager 不设置 setOffscreenPageLimit 或设置数量不够
        // 销毁的Fragment onCreateView 每次都会执行(但实体类没有从内存销毁)
        // 导致initData反复执行,所以这里注释掉
        // isFirstLoad = true;
        if (mFragmentView == null) {
            initLoadingDialog();
            mFragmentView = initViews(inflater, container, savedInstanceState);
            isPrepared = true;
            hasCancel = false;
            lazyLoad();
        }
        return mFragmentView;
    }


    public View onCreateView(int layoutResID, LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout linear = new RelativeLayout(getActivity());

        View view = inflater.inflate(layoutResID, container, false);
        linear.addView(view);

        //加载进度view
        AutoRelativeLayout.LayoutParams loading_activity_state_params = new AutoRelativeLayout.LayoutParams(AutoRelativeLayout.LayoutParams.MATCH_PARENT, AutoRelativeLayout.LayoutParams.MATCH_PARENT);
        loading_activity_state_params.addRule(AutoRelativeLayout.CENTER_IN_PARENT);
        loading_activity_state = inflater.inflate(R.layout.loading_activity_state, container, false);
        findTitleViewLoaddingActivityStateById(loading_activity_state);
        linear.addView(loading_activity_state, loading_activity_state_params);
        return linear;
    }

    /**
     * 显示LoadingDiog
     *
     * @param title
     */
    public void showLoadingDialog(String title) {
        if (mListener != null)
            mListener.showLoginDialog(title);
//        if (null != this && !this.isFinishing()) {
//            if (!TextUtils.isEmpty(title)) {
//                abLoadingDialog.setTitle(title);
//            } else {
//                abLoadingDialog.setTitle(getString(R.string.loding_dialog));
//            }
//            abLoadingDialog.show();
//        }
//        _loadingDialog.showWithStatus(title, SVProgressHUD.SVProgressHUDMaskType.None);
    }

    /**
     * 隐藏LoadingDiog
     */
    public void dismissLoadingDialog() {
        if (mListener != null)
            mListener.dismissloading();
    }

    /**
     * 初始化正在加载进度条
     */
    public void initLoadingDialog() {
        //abLoadingDialog = new AbLoadingDialog(getActivity());
        //abLoadingDialog.setCancelable(false);
//        abLoadingDialog.setCancelable(true);
//        abLoadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            public boolean onKey(DialogInterface dialog,
//                                 int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    dialog.dismiss();
//                    //此处把dialog dismiss掉，然后把本身的activity finish掉
//                    getActivity().finish();
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//        _loadingDialog = new SVProgressHUD(getActivity());
//        _loadingDialog.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss(SVProgressHUD hud) {
//                if (getCurrentPersenter() != null) {
//                    getCurrentPersenter().unsubcrib();
//                }
//            }
//        });
    }

    /**
     * 一般提示消息
     *
     * @param msg
     */
    public void showInfoMsg(String msg) {
        if (mListener != null)
            mListener.showInfoMessage(msg);
//        _loadingDialog.showInfoWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
    }

    /**
     * 成功提示消息
     *
     * @param msg
     */
    public void showSuccessMsg(String msg) {
        if (mListener != null)
            mListener.showSuccessMessage(msg);
//        _loadingDialog.showSuccessWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
    }

    /**
     * 错误提示消息
     *
     * @param msg
     */
    public void showErrMsg(String msg) {
        if (mListener != null)
            mListener.showErrorMessageg(msg);
//        _loadingDialog.showErrorWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
    }


    private void findTitleViewLoaddingActivityStateById(View view) {
        ll_loadding_activity_state_loading = (LinearLayout) view.findViewById(R.id.ll_loadding_activity_state_loading);
        tv_loadding_activity_state_loading_text = (TextView) view.findViewById(R.id.tv_loadding_activity_state_loading_text);
        ll_loadding_activity_state_nodata = (LinearLayout) view.findViewById(R.id.ll_loadding_activity_state_nodata);
        tv_loadding_activity_state_nodata_text = (TextView) view.findViewById(R.id.tv_loadding_activity_state_nodata_text);
        ll_loadding_activity_state_network_error = (LinearLayout) view.findViewById(R.id.ll_loadding_activity_state_network_error);
        tv_loadding_activity_state_network_error_text = (TextView) view.findViewById(R.id.tv_loadding_activity_state_network_error_text);
        btn_loadding_activity_state_network_error_retry = (Button) view.findViewById(R.id.btn_loadding_activity_state_network_error_retry);
        view.setVisibility(View.GONE);
    }

    /**
     * 启动加载
     *
     * @param loadingText 加载文本
     */
    public void startLoading(String loadingText) {
        loading_activity_state.setVisibility(View.VISIBLE);
        ll_loadding_activity_state_loading.setVisibility(View.VISIBLE);
        ll_loadding_activity_state_nodata.setVisibility(View.GONE);
        ll_loadding_activity_state_network_error.setVisibility(View.GONE);
        tv_loadding_activity_state_loading_text.setText(loadingText);
    }

    /**
     * 启动加载
     */
    public void startLoading() {
        loading_activity_state.setVisibility(View.VISIBLE);
        ll_loadding_activity_state_loading.setVisibility(View.VISIBLE);
        ll_loadding_activity_state_nodata.setVisibility(View.GONE);
        ll_loadding_activity_state_network_error.setVisibility(View.GONE);
        tv_loadding_activity_state_loading_text.setText("正在加载...");
    }

    /**
     * 停止加载
     */
    public void stopLoading() {
        loading_activity_state.setVisibility(View.GONE);
        ll_loadding_activity_state_loading.setVisibility(View.GONE);
        ll_loadding_activity_state_nodata.setVisibility(View.GONE);
        ll_loadding_activity_state_network_error.setVisibility(View.GONE);
    }

    /**
     * 设置加载无数据
     *
     * @param noDataText 加载无数据文本
     */
    public void setLoadingNoData(String noDataText) {
        loading_activity_state.setVisibility(View.VISIBLE);
        ll_loadding_activity_state_loading.setVisibility(View.GONE);
        ll_loadding_activity_state_nodata.setVisibility(View.VISIBLE);
        ll_loadding_activity_state_network_error.setVisibility(View.GONE);
        tv_loadding_activity_state_nodata_text.setText(noDataText);
    }

    /**
     * 设置加载异常
     *
     * @param netErrorText 加载异常文本
     */
    public void setLoadingNetError(String netErrorText) {
        loading_activity_state.setVisibility(View.VISIBLE);
        ll_loadding_activity_state_loading.setVisibility(View.GONE);
        ll_loadding_activity_state_nodata.setVisibility(View.GONE);
        ll_loadding_activity_state_network_error.setVisibility(View.VISIBLE);
        tv_loadding_activity_state_network_error_text.setText(netErrorText);
        btn_loadding_activity_state_network_error_retry.setText("重试");
    }

    /**
     * 设置加载异常
     */
    public void setLoadingNetError() {
        loading_activity_state.setVisibility(View.VISIBLE);
        ll_loadding_activity_state_loading.setVisibility(View.GONE);
        ll_loadding_activity_state_nodata.setVisibility(View.GONE);
        ll_loadding_activity_state_network_error.setVisibility(View.VISIBLE);
        tv_loadding_activity_state_network_error_text.setText("网络异常,点击按钮重试");
        btn_loadding_activity_state_network_error_retry.setText("重试");
    }

    /**
     * 重新加载按钮
     *
     * @return
     */
    public Button retryButton() {
        return btn_loadding_activity_state_network_error_retry;
    }

    public int getLoadingStatus() {
        return loadingStatus;
    }

    public void setLoadingStatus(int loadingStatus) {
        this.loadingStatus = loadingStatus;
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    OnActivitySelectListenner mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnActivitySelectListenner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implementOnArticleSelectedListener");
        }
    }

    protected void onVisible() {
        startTime = System.currentTimeMillis();
        if (!hasCancel) {
            lazyLoad();
        }
        hasCancel = false; //重置标示位
        /*MyApplication.getUIHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!hasCancel) {
                    lazyLoad();
                }
                hasCancel = false; //重置标示位
            }
        }, 10);*/
    }

    protected void onInvisible() {

    }

    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initData();
    }

    protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void initData();

    public abstract void RefreshFragment();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewGroup parent = (ViewGroup) mFragmentView.getParent();
        if (parent != null) {
            parent.removeView(mFragmentView);
        }
    }
}
