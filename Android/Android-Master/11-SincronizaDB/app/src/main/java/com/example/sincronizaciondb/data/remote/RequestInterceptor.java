package com.example.sincronizaciondb.data.remote;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();      //copiamos request
        HttpUrl originalHttpUrl = originalRequest.url();    //copiamos url del request

        HttpUrl url = originalHttpUrl.newBuilder()  //Cremos un nuevo url
                .addQueryParameter("api_key",ApiConstants.API_KEY)  //concatenamos clave
                .build();

        Request request = originalRequest.newBuilder()  //creamos un nuevo request
                .url(url)   //añadimos url con clave
                .build();

        return chain.proceed(request);
    }
}
