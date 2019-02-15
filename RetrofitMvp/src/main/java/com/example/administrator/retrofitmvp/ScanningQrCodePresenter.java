package com.example.administrator.retrofitmvp;

import android.app.Activity;

/**
 * 首页 扫描 二维码
 */
public class ScanningQrCodePresenter extends RxPresenter<ScanningQrCodeContract.View> implements ScanningQrCodeContract.Presenter {

    @Override
    public void requestData(
            String site,
            String tel,
            String password

    ) {
        Api.getDefault().addLoginPeople(site, tel, password)
                .compose(RxHelper.<AddLoginPeopleBean>handleResult())
                .subscribe(new RxObserver<AddLoginPeopleBean>((Activity) mView) {
                    @Override
                    public void _onNext(AddLoginPeopleBean addLoginPeopleBean) {
                        mView.refreshView(addLoginPeopleBean);
                    }

                    @Override
                    public String _onMessage() {
                        return null;
                    }

                    @Override
                    public void _onError() {

                    }
                });
    }

}
