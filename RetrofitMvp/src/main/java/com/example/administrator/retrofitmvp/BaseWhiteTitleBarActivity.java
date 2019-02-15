package com.example.administrator.retrofitmvp;

import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;

/**
 *
 * 白色标题栏沉浸式效果父类
 *
 */

public abstract class BaseWhiteTitleBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化沉浸式
        initImmersive();
    }

    /**
     * 初始化沉浸式
     */
    private void initImmersive() {
        mImmersionBar.statusBarView(findViewById(R.id.top_view)).init();
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.1f)
                .init();
    }

}
