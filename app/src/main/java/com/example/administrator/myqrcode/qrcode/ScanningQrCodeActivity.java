package com.example.administrator.myqrcode.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myqrcode.R;
import com.example.administrator.myqrcode.qrcode.util.Constant;
import com.example.administrator.myqrcode.qrcode.zxing.activity.CaptureActivity;
import com.example.administrator.retrofitmvp.AddLoginPeopleBean;
import com.example.administrator.retrofitmvp.BaseWhiteTitleMvpActivity;
import com.example.administrator.retrofitmvp.LogUtil;
import com.example.administrator.retrofitmvp.ScanningQrCodeContract;
import com.example.administrator.retrofitmvp.ScanningQrCodePresenter;
import com.example.administrator.retrofitmvp.SystemUtil;
import com.example.administrator.retrofitmvp.ToastUtil;


/**
 * 首页 扫描 二维码
 */
public class ScanningQrCodeActivity extends BaseWhiteTitleMvpActivity<ScanningQrCodePresenter> implements ScanningQrCodeContract.View, View.OnClickListener {

    private static final String TAG = "----->ScanningQrCodeActivity";

    private RelativeLayout rlt_qrcode_container;
    private TextView tv_qr;
    private TextView tv_hint;


    private LinearLayout llt_bottom_back;
    private ImageView iv_bottom_back;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llt_bottom_back:
                onFinish();
                break;
            case R.id.iv_bottom_back:
                onFinish();
                break;
            case R.id.tv_qr:
                scanQrCode();
                break;
        }
    }

    @Override
    public ScanningQrCodePresenter initPresenter() {
        return new ScanningQrCodePresenter();
    }

    @Override
    protected int loadTitleBarXml() {
        return 0;
    }

    @Override
    protected void initTitleBar() {
        titleBar_title.setText("扫一扫");
    }

    @Override
    protected int loadContentXml() {
        return R.layout.scanning_qrcode_layout;
    }

    @Override
    protected Activity initViews() {

        rlt_qrcode_container = findViewById(R.id.rlt_qrcode_container);
        tv_qr = findViewById(R.id.tv_qr);
        tv_hint = findViewById(R.id.tv_hint);


        llt_bottom_back = findViewById(R.id.llt_bottom_back);
        iv_bottom_back = findViewById(R.id.iv_bottom_back);

        llt_bottom_back.setOnClickListener(this);
        iv_bottom_back.setOnClickListener(this);
        tv_qr.setOnClickListener(this);

//        tv_qr.setText("当前网络不可用" + "\n" + "请检查网络设置");
//        rlt_qrcode_container.setVisibility(View.INVISIBLE);

//        scanQrCode();

        // 二维码扫码
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);

        return this;
    }


    private void scanQrCode() {
        //如果网络不可用
        if (!SystemUtil.isNetworkConnected(this)) {
            rlt_qrcode_container.setVisibility(View.VISIBLE);
        } else {
            // 二维码扫码
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, Constant.REQ_QR_CODE);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String site = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
//            ToastUtil.showToastSHORT("--->site " + site);
            LogUtil.logDebug(TAG, "--->site " + site);

            //扫码成功
            if (!TextUtils.isEmpty(site)) {

                ToastUtil.showToastSHORT(site);

                setResult(1000, new Intent().putExtra("site", site));
                finish();

            } else {
                //失败
                rlt_qrcode_container.setVisibility(View.VISIBLE);
            }

            LogUtil.logDebug(TAG, "--->onActivityResult ");
            onFinish();

        } else {
            onFinish();
        }
    }

    @Override
    public void refreshView(AddLoginPeopleBean addLoginPeopleBean) {
        if (addLoginPeopleBean != null) {
            String message = addLoginPeopleBean.getMessage();
            int status = addLoginPeopleBean.getStatus();

            if (status != 401) {
                ToastUtil.showToastSHORT(message);
            } else {
                ToastUtil.showToastSHORT("扫码失败，请稍后重试！");
            }
        } else {
            ToastUtil.showToastSHORT("扫码失败，请稍后重试！");
        }

        onFinish();
    }

    private void onFinish() {
        finish();
    }

    @Override
    public void onBackPressed() {
        onFinish();
    }
}
