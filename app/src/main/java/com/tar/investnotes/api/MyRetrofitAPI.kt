package com.tar.investnotes.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface MyRetrofitAPI {

    @Headers(
        "x-rapidapi-key: c4db88c9b8mshcb851f56e93ca4ap1f007bjsn345b0b380bd9",
        "x-rapidapi-host: apidojo-yahoo-finance-v1.p.rapidapi.com"
    )
    @GET
    fun getStockInfo(@Url url: String): Observable<ResponseBody?>?

//    @Headers(
//        "x-rapidapi-key: c4db88c9b8mshcb851f56e93ca4ap1f007bjsn345b0b380bd9",
//        "x-rapidapi-host: apidojo-yahoo-finance-v1.p.rapidapi.com"
//    )
    @GET
    fun getTestInfo(@Url url: String): Call<ResponseBody?>?

//    @GET
//    fun getStockInfo(@Url url: String): Call<ResponseBody?>?
}