package com.zyl.tradeplatform.ui.mvp.login;

/**
 * Created by Administrator on 2017/2/26.
 */

import com.zyl.tradeplatform.base.BaseModel;
import com.zyl.tradeplatform.base.BasePresenter;
import com.zyl.tradeplatform.base.BaseView;
import com.zyl.tradeplatform.bean.ResponseResult;
import com.zyl.tradeplatform.bean.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * 契约类
 */
public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter{
        abstract void login(String mobile,String password);
    }

    interface LoginView extends BaseView{
        void loginSuccess(ResponseResult<UserEntity> userEntity);
        void loginError();
    }

    interface LoginModle extends BaseModel{
        @GET("")
        Observable<ResponseResult<UserEntity>> login(@Query("mobile") String mobile, @Query("password") String password);
    }
}
