package com.desperado.tastylattlelib.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.desperado.tastylattlelib.R;

import java.util.HashMap;
import java.util.Map;


/**
 * @author
 */
public abstract class BaseActivity extends ToolBarActivity implements OnActivitySelectListenner {

    /**
     * 判断是否上拉下拉加载的状态 来自网络下载加载:TYPE_IS_FROM_NET,上拉加载:TYPE_IS_FROM_PULLUP
     */
    public int loadingStatus = 0;//
    public static final String INTENT_OBJECT = "intent_object";
    public static final String INTENT_BUNDLE = "intent_bundle";
    public String[] items = {"分享微信好友", "分享微信朋友圈"};


    private Map<Integer, Runnable> allowablePermissionRunnables = new HashMap<>();
    private Map<Integer, Runnable> disallowablePermissionRunnables = new HashMap<>();
    private SVProgressHUD _loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        initLoadingDialog();
    }

    @Override
    public void setContentView(View _mUserView) {
        super.setContentView(_mUserView);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.common_title_blue), LocalConstant.ALL_TRANSPARENT_ALPHA);
    }


    @Override
    public void setContentView(int layoutResID) {
        View _mUserView = LayoutInflater.from(this).inflate(layoutResID, null, false);
        setContentView(_mUserView);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (_loadingDialog != null && !_loadingDialog.isShowing()) {
                return super.onKeyDown(keyCode, event);
            } else {
                return false;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化正在加载进度条
     */
    public void initLoadingDialog() {
//
//        abLoadingDialog = new AbLoadingDialog(this);
//        abLoadingDialog.setCancelable(true);
//        abLoadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            public boolean onKey(DialogInterface dialog,
//                                 int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    dialog.dismiss();
////                    //此处把dialog dismiss掉，然后把本身的activity finish掉
//                    BaseActivity.this.finish();
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
        _loadingDialog = new SVProgressHUD(this);
//        _loadingDialog.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss(SVProgressHUD hud) {
////                if (getCurrentPersenter() != null) {
////                    getCurrentPersenter().unsubcrib();
////                }
//            }
//        });
    }


    public int getLoadingStatus() {
        return loadingStatus;
    }

    /**
     * 设置是否下拉刷新，还是直接加载
     *
     * @param loadingStatus
     */
    public void setLoadingStatus(int loadingStatus) {
        this.loadingStatus = loadingStatus;
    }

    /**
     * 显示LoadingDiog
     *
     * @param title
     */
    public void showLoadingDialog(String title) {
//        if (null != this && !this.isFinishing()) {
//            if (!TextUtils.isEmpty(title)) {
//                abLoadingDialog.setTitle(title);
//            } else {
//                abLoadingDialog.setTitle(getString(R.string.loding_dialog));
//            }
//            abLoadingDialog.show();
//        }
        _loadingDialog.showWithStatus(title, SVProgressHUD.SVProgressHUDMaskType.None);
    }

    /**
     * 一般提示消息
     *
     * @param msg
     */
    public void showInfoMsg(String msg) {
        _loadingDialog.showInfoWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
    }

    /**
     * 成功提示消息
     *
     * @param msg
     */
    public void showSuccessMsg(String msg) {
        _loadingDialog.showSuccessWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
    }

    /**
     * 错误提示消息
     *
     * @param msg
     */
    public void showErrMsg(String msg) {
        _loadingDialog.showErrorWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
    }

    /**
     * 一般提示消息
     *
     * @param msg
     */
    public void showInfoMsg(String msg, final OnDialogDismissListener _listener) {
        _loadingDialog.showInfoWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
        _loadingDialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(SVProgressHUD hud) {
                if (_listener != null)
                    _listener.onDialogDismiss();
            }
        });
    }


    /**
     * 成功提示消息
     *
     * @param msg
     */
    public void showSuccessMsg(String msg, final OnDialogDismissListener _listener) {
        _loadingDialog.showSuccessWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
        _loadingDialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(SVProgressHUD hud) {
                if (_listener != null)
                    _listener.onDialogDismiss();
            }
        });
    }


    /**
     * 错误提示消息
     *
     * @param msg
     */
    public void showErrMsg(String msg, final OnDialogDismissListener _listener) {
        _loadingDialog.showErrorWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.Gradient);
        _loadingDialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(SVProgressHUD hud) {
                if (_listener != null) {
                    _listener.onDialogDismiss();
                }
            }
        });
    }



    /**
     * 隐藏LoadingDiog
     */
    public void dismissLoadingDialog() {
        if (null != _loadingDialog && _loadingDialog.isShowing()) {
            _loadingDialog.dismissImmediately();
        }
    }

    /**
     * 初始化标题
     **/
    protected abstract void initTitleBar();

    /**
     * 绑定界面UI
     **/
    protected abstract void initfindViewById();

    /**
     * 界面UI事件监听
     **/
    protected abstract void setListener();

    /**
     * 界面数据初始化
     **/
    protected abstract void init();

    protected abstract Activity getActivity();

    protected abstract BasePresenter getCurrentPersenter(); //获取当前的业务处理类

    /**
     * 请求权限
     *
     * @param id                   请求授权的id 唯一标识即可
     * @param permission           请求的权限
     * @param allowableRunnable    同意授权后的操作
     * @param disallowableRunnable 禁止权限后的操作
     */
    public void requestPermission(final int id, String permissionIntroduce, final String permission, Runnable allowableRunnable, Runnable disallowableRunnable) {
        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }

        allowablePermissionRunnables.put(id, allowableRunnable);
        if (disallowableRunnable != null) {
            disallowablePermissionRunnables.put(id, disallowableRunnable);
        }

        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //减少是否拥有权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(BaseActivity.this, new String[]{permission}, id);
                return;
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)) {
//                    showMessageOKCancel(permissionIntroduce, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions(BaseActivity.this, new String[]{permission}, id);
//                        }
//                    });
//                }else{

//                }
            } else {
                allowableRunnable.run();
            }
        } else {
            allowableRunnable.run();
        }

    }


    @Override
    protected void onDestroy() {
        /**在activity结束前，先注销事件订阅,先关闭dialog**/

        if (getCurrentPersenter() != null) {
            getCurrentPersenter().unsubcrib();

        }
        AppManager.getInstance().killActivity(this);
        super.onDestroy();
        //当当前的页面关闭时，结束当前页面未执行完的请求任务
    }

    @Override
    public void showErrorMessageg(String error) {
        showErrMsg(error);
    }

    @Override
    public void showLoginDialog(String message) {
        showLoadingDialog(message);
    }

    @Override
    public void showSuccessMessage(String msg) {
        showSuccessMsg(msg);
    }

    @Override
    public void showInfoMessage(String msg) {
        showInfoMsg(msg);
    }

    @Override
    public void dismissloading() {
        dismissLoadingDialog();
    }
}
