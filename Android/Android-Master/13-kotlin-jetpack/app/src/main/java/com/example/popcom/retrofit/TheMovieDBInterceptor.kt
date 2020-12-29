package com.example.popcom.retrofit

import com.example.popcom.common.constants
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(constants.URL_PARAM_API_KEY,constants.API_KEY)
            .addQueryParameter(constants.URL_PARAM_LANG,constants.SPANISH_MEX)
            .build()

        var request =chain.request()

        request = request?.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type","application/json")
            .addHeader("Accept","application/json")
            .build()

        return chain.proceed(request)
    }
}