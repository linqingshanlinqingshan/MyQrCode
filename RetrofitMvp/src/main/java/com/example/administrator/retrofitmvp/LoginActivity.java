package com.example.administrator.retrofitmvp;

import android.app.Activity;

import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private static final String TAG = "----->LoginActivity";

    private EditText et_name;
    private EditText et_password;
    private TextView tv_txt;
    private TextView tv_ok;

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.tv_ok) {

            String name_txt = et_name.getText().toString().trim();
            String password_txt = et_password.getText().toString().trim();

            mPresenter.login(name_txt, password_txt);

        }

    }

    @Override
    protected int loadTitleBarXml() {
        return 0;
    }

    @Override
    protected void initTitleBar() {
        titleBar_title.setText("xxxxx");
    }

    @Override
    protected int loadContentXml() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.login_layout;
    }

    @Override
    protected Activity initViews() {

        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        tv_txt = findViewById(R.id.tv_txt);
        tv_ok = findViewById(R.id.tv_ok);

        tv_ok.setOnClickListener(this);

        return this;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void refreshView(List<ShopBrandAdverBean.DataBean> dataBeanList, String name, String password) {

        if (dataBeanList != null) {
            int size = dataBeanList.size();
            for (int i = 0; i < size; i++) {
                String id = dataBeanList.get(i).getId();
                String sort = dataBeanList.get(i).getSort();
                String type = dataBeanList.get(i).getType();
                String update_url = dataBeanList.get(i).getUpdate_url();

                LogUtil.logDebug(TAG, "--->id = " + id);
                LogUtil.logDebug(TAG, "--->sort = " + sort);
                LogUtil.logDebug(TAG, "--->type = " + type);
                LogUtil.logDebug(TAG, "--->update_url = " + update_url);

                tv_txt.setText(
                        "name = " + name + "  password = " + password +
                                "--->id = " + id +
                                "--->sort = " + sort +
                                "--->type = " + type +
                                "--->update_url = " + update_url
                );
            }
        }
    }
}