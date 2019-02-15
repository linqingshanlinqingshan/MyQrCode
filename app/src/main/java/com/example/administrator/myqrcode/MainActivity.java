package com.example.administrator.myqrcode;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myqrcode.qrcode.ScanningQrCodeActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_qr = findViewById(R.id.tv_qr);
        tv_qr.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_qr:
                new RxPermissions(this)
                        .request(
                                android.Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull Boolean aBoolean) throws Exception {
                                if (!aBoolean) {
                                    Toast.makeText(MainActivity.this, "请先去设置权限！", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    openCamera();
                                }
                            }
                        });
                break;
        }
    }

    private void openCamera() {

        startActivityForResult(new Intent(this, ScanningQrCodeActivity.class), 1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (data != null) {
                String site = data.getStringExtra("site");
                if (!TextUtils.isEmpty(site)) {
                    tv_qr.setText(site);
                }
            }
        }
    }
}
