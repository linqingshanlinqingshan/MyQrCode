package com.example.administrator.retrofitmvp;

/**
 * 首页 扫描 二维码
 */
public interface ScanningQrCodeContract {

    interface View extends BaseView {
        void refreshView(AddLoginPeopleBean addLoginPeopleBean);
    }

    interface Presenter extends BasePresenter<View> {
        void requestData(String site,
                         String tel,
                         String password);
    }
}
