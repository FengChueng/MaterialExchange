package com.zyl.tradeplatform.http.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.zyl.tradeplatform.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zhouyou on 2016/11/21.
 * Class desc:
 *
 * 自定义Retrofit的Gson解析
 */
public class CustomResponseConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public CustomResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonString = value.string();
        LogUtils.e("test", "jsonString: " + jsonString);
        try {
            JSONObject json = new JSONObject(jsonString);
            int status = json.getInt("status");
            String msg = json.getString("msg");
            LogUtils.e("test", "code: " + status);
            LogUtils.e("test", "msg: " + msg);
            if (0 == status) {
                // 请求成功,返回数据
                return adapter.fromJson(jsonString);
            } else {
                throw new RuntimeException(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            value.close();
        }
        return null;
    }

}
