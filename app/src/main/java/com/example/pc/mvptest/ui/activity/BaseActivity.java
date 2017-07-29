package com.example.pc.mvptest.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.umeng.analytics.MobclickAgent;

/**
 * @author
 * @version 1.0
 * @date 2017/7/18
 */
public class BaseActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
