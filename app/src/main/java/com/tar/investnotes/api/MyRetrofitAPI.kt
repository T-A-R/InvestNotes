package com.tar.investnotes.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface MyRetrofitAPI {

    @GET
    fun getStockInfo(@Url url: String): Observable<ResponseBody?>?

//    @GET
//    fun getStockInfo(@Url url: String): Call<ResponseBody?>?
}