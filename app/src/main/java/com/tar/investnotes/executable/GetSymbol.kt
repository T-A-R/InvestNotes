package com.tar.investnotes.executable

import android.net.Uri
import android.os.Process
import android.util.Log
import com.google.gson.Gson
import com.tar.investnotes.models.SuggestionQuery
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.IOException
import java.util.regex.Pattern

class GetSymbol(threadName: String, private val mQuery: String) : Thread(threadName) {

    companion object {
        // http://autoc.finance.yahoo.com/autoc?query=ab&callback=YAHOO.Finance.SymbolSuggest.ssCallback&region=US&lang=en-US
        private const val BASE_URL = "http://autoc.finance.yahoo.com/autoc?"
        private const val QUERY = "query"
        private const val CALLBACK = "callback"
        private const val REGION = "region"
        private const val LANGUAGE = "lang"
    }

    override fun run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
        if (!isInterrupted) {
            val callback = "YAHOO.Finance.SymbolSuggest.ssCallback"
            val region = "US"
            val language = "en-US"

            // build suggestion query uri
            val querySuggestionUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY, mQuery)
                .appendQueryParameter(CALLBACK, callback)
                .appendQueryParameter(REGION, region)
                .appendQueryParameter(LANGUAGE, language)
                .build()
            Log.d("T-L.GetSymbolSuggestion", "===============: $querySuggestionUri")

            // execute the connection and retrieve the data
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(querySuggestionUri.toString()).build()
                Log.d("T-L.GetSymbol", "Request: $request")
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    Log.d("T-L.GetSymbol", "Response: $response")
                    val `in` = response.body()!!.charStream()
                    val reader = BufferedReader(`in`)

                    // convert the resultant response into an object gson can parse
                    // 1. convert the reader into a string
                    val sb = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        sb.append(line)
                    }

                    // 2. strip out Yahoo string at beginning of the response
                    val pattern = Pattern.compile("YAHOO\\.Finance\\.SymbolSuggest\\.ssCallback\\((\\{.*?\\})\\)")
                    val matcher = pattern.matcher(sb)
                    if (matcher.find()) {
                        val suggestionQuery = Gson().fromJson(matcher.group(1), SuggestionQuery::class.java)
                        val symbols = suggestionQuery.resultSet!!.result
                        if (symbols != null) {
                            if (symbols.size > 0) {
                                //TODO SEND symbols
                            } else {
                                Log.d("T-L.GetSymbol", "No matching records found")
                            }
                        } else {
                            Log.d("T-L.GetSymbol", "No results found")
                        }
                    }
                } else {
                    Log.d("T-L.GetSymbol", "Http response: $response")
                }
            } catch (e: IOException) {
                Log.d("T-L.GetSymbol", "Error executing connection: " + e.message)
            }

            //TODO SEND COMPLETE
        }
    }


}