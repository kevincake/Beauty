package cn.ifreedomer.beauty.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.progress.ProgressCancelListener;
import cn.ifreedomer.beauty.progress.ProgressDialogHandler;
import cn.ifreedomer.beauty.util.ToastUtil;

public class BaseActivity extends AppCompatActivity implements ProgressCancelListener{

    public Toolbar mActionBarToolbar;
    private ProgressDialogHandler progressDialogHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityStackManager.getScreenManager().pushActivity(this);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        progressDialogHandler = new ProgressDialogHandler(this,this,true);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard();
            }
        });

    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.phone_toolbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
                mActionBarToolbar.setNavigationIcon(R.mipmap.back);
                mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        onBackPressed();
                    }
                });
                mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.white));
            }
        }

        return mActionBarToolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 显示软键盘
     */
    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
                    .getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getScreenManager().popActivity(this);
    }



    /**
     * 退出所有activity栈
     */
    public static void finishAll() {
//        ActivityStackManager.getScreenManager().popAllActivityExceptOne(null);
    }

    /**
     * 退出程序
     */
    public static void exitApp() {
        finishAll();
//		System.exit(0);
    }

    public  void showLoading(){
        progressDialogHandler.showProgressDialog();
    };


    public  void showLoading(String title){
        progressDialogHandler.showProgressDialog(title);
    };

    public void dissmissLoading(){
        progressDialogHandler.dismissProgressDialog();
    }


    /**
     *
     */
    public void showErrorMsg(String errorMsg) {
        ToastUtil.showTextToast(this, errorMsg);
    }

    @Override
    public void onCancelProgress() {

    }
}
