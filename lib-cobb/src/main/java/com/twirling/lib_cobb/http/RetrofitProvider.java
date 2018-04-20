package com.twirling.lib_cobb.http;

import com.blankj.utilcode.util.StringUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Target: 提供Retrofit的接口
 *
 * @author qiupengxie
 */
public class RetrofitProvider {
    private static String ENDPOINT = "";

    private RetrofitProvider() {
    }

    /**
     * 1
     *
     * @return
     */
    public static Api getService() {
        return RetrofitProvider.getInstance()
                .create(Api.class);
    }

    public static Retrofit setBaseUrl(String endpoint) {
        ENDPOINT = endpoint;
        return SingletonHolder.INSTANCE;
    }

    /**
     * 2
     *
     * @return
     */
    public static Retrofit getInstance() {
        if (StringUtils.isEmpty(ENDPOINT)) {
            throw new RuntimeException("Retrofit not init api server!");
        }
        return SingletonHolder.INSTANCE;
    }

    /**
     * Target: 提供唯一的Retrofit单例
     */
    private static class SingletonHolder {
        private static final Retrofit INSTANCE = create();
        private static final int TIME_OUT = 2000;

        private static Retrofit create() {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
//                    .addNetworkInterceptor(TokenInterceptor.create())
                    .build();
            return new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }
}
