package com.zyl.tradeplatform.ui.mvp.login;

import com.zyl.tradeplatform.bean.ResponseResult;
import com.zyl.tradeplatform.bean.UserEntity;
import com.zyl.tradeplatform.http.api.Api;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/2/26.
 */

public class LoginModelImpl implements LoginContract.LoginModle{
    LoginContract.LoginModle service = Api.getInstance().createService(LoginContract.LoginModle.class);
    @Override
    public Observable<ResponseResult<UserEntity>> login(@Query("mobile") String mobile, @Query("password") String password) {
        return service.login(mobile,password);
    }
}
