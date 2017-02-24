package com.zyl.tradeplatform.http.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zyl.tradeplatform.App;
import com.zyl.tradeplatform.constants.ApiConstant;
import com.zyl.tradeplatform.constants.AppConfig;
import com.zyl.tradeplatform.http.gson.CustomGsonConverterFactory;
import com.zyl.tradeplatform.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;


/**
 * 配置okhttp
 */
public class Api {

    /** 是否设置缓存 */
    private static final boolean mIsSetCache = false;

    private static Api mInstance = null;
    private Retrofit mRetrofit = null;

    /**
     * 获取 Api 实例（单例，使用默认服务器地址）
     * @return Api 对象
     */
    public static Api getIns(){
        return getIns(null);
    }

    /**
     * 获取 Api 实例
     * @param baseUrl 服务器地址
     * @return
     */
    public static Api getIns(String baseUrl){
        if (mInstance == null) {
            synchronized (Api.class){
                if(mInstance == null){
                    mInstance = new Api(baseUrl);
                }
            }
        }
        return mInstance;
    }

    private Api(String baseUrl){
        if(baseUrl != null){
            configRetrofit(baseUrl);
        }else{
            configRetrofit(ApiConstant.API_SERVER_URL);
        }
    }

    /**
     * 配置Retrofit
     * @param baseUrl 服务器地址
     */
    private void configRetrofit(String baseUrl){
        // 创建 OKHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置头
        Interceptor headInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder()
                        .addHeader("X-LC-Id", "")
                        .addHeader("X-LC-Key", "")
                        .addHeader("Content-Type", "application/json")
                        .build());
            }
        };
        builder.addInterceptor(headInterceptor);

        // 设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        // 设置Log信息拦截器
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logInterceptor);

        // 设置错误重连
        builder.retryOnConnectionFailure(true);

        // 设置缓存
        if(mIsSetCache){
            File cacheFile = new File(AppConfig.CACHE_PATH);
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            builder.cache(cache).addInterceptor(new HttpCacheInterceptor());
        }


        // 构建Retrofit
        OkHttpClient okHttpClient = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * 创建Api接口
     * @param clz api接口
     * @return
     */
    public <T> T createService(Class<T> clz){
        return mRetrofit.create(clz);
    }

    /**
     * 缓存拦截器
     */
    class HttpCacheInterceptor implements Interceptor{
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtils.networkIsAvailable(App.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetUtils.networkIsAvailable(App.getInstance())) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("zhouyou")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("nyn")
                        .build();
            }
            return response;
        }
    }

}
