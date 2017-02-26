package com.zyl.tradeplatform.ui.mvp.login;

/**
 * Created by Administrator on 2017/2/26.
 */

public class LoginPresenterImpl extends LoginContract.LoginPresenter{
    LoginContract.LoginView mLoginView;
    LoginModelImpl mLoginModel;
    public LoginPresenterImpl(LoginContract.LoginView mLoginView){
        this.mLoginView = mLoginView;
        mLoginModel = new LoginModelImpl();
    }
    @Override
    void login(String mobile, String password) {
        
    }
}
