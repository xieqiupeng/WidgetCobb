package com.twirling.lib_cobb.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author qiupengxie
 */
public class TokenInterceptor implements Interceptor {
    private final String USER_TOKEN = "Token";
    private String token = "";

    public TokenInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request originalRequest = chain.request();
        if (token.isEmpty() || originalRequest.header(USER_TOKEN) != null) {
            return chain.proceed(originalRequest);
        }
        Request request = originalRequest.newBuilder()
                .header(USER_TOKEN, token)
                .build();
        return chain.proceed(request);
    }

    public static TokenInterceptor create(String token) {
        return new TokenInterceptor(token);
    }
}