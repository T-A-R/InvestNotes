package com.tar.investnotes.api

import android.util.Log

import com.tar.investnotes.CoreApp
import com.tar.investnotes.activities.MainActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MyAPI {

    /**
     * Get Index
     */
//    @JvmStatic
//    fun getStockInfo(url: String, listener: GetStockInfoCallback?) {
//        CoreApp.retrofitAPI?.getStockInfo(url)?.enqueue(object : Callback<ResponseBody?> {
//            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
//                Log.d(MainActivity.TAG, "MyAPI.getStockInfo.onResponse() $url Message: ${response.message()}")
//                listener?.onGetStockInfo(response.body())
//            }
//
//            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
//                Log.d(MainActivity.TAG, "MyAPI.getStockInfo.onFailure(): $t")
//                listener?.onGetStockInfo(null)
//            }
//        })
//    }
//
//    interface GetStockInfoCallback {
//        fun onGetStockInfo(data: ResponseBody?)
//    }
}