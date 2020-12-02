package com.tar.investnotes.fragments

import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.underscore.lodash.U
import com.google.gson.GsonBuilder
import com.tar.investnotes.Constants
import com.tar.investnotes.R
import com.tar.investnotes.activities.MainActivity.Companion.TAG
import com.tar.investnotes.adapters.StockAdapter
import com.tar.investnotes.api.MyAPI
import com.tar.investnotes.api.responses.index.Data
import com.tar.investnotes.api.responses.index.Document
import com.tar.investnotes.api.responses.index.IndexObject
import com.tar.investnotes.database.models.InvestmentR
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import kotlinx.android.synthetic.main.fragment_stock.*
import okhttp3.ResponseBody

//class StockFragment : SmartFragment(R.layout.fragment_stock), MyAPI.GetStockInfoCallback {
class StockFragment : SmartFragment(R.layout.fragment_stock) {

    private var investList : List<InvestmentR>? = null

    override fun onReady() {
        investList = getDao().getAllInvestments()

        initViews()
        MainFragment.enableSideMenu()
        showMenu()
//        btn_next.setOnClickListener { onNextButtonClick() }

//        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)
//        GlobalScope.launch {
//            println("============= STOCK ME: " + getMainActivity().apiClient.etfsCountryExposure("AAPL").toString())
//        }

//        MyAPI.getStockInfo(Constants.Default.API_ME_INDEX_URL + "AAPL" + Constants.Default.API_ME_INDEX_PARAMS, this)

    }

    private fun initViews() {
        labelText.typeface = Fonts.getKallisto()
//        btn_next.typeface = Fonts.getFuturaPtBook()
//        btn_next.transformationMethod = null
        cont_temp_fragment.startAnimation(Anim.getAppear(context))
        labelText.startAnimation(Anim.getAppearSlide(context, 200))

        if(!investList.isNullOrEmpty()) {
            stockRecyclerView.layoutManager = LinearLayoutManager(getMainActivity(), RecyclerView.VERTICAL, false)
            val adapter = StockAdapter(investList!!)
            stockRecyclerView.adapter = adapter
        }
    }

    fun onNextButtonClick() {
        Log.d(TAG, "onNextButtonClick: ")
        showMenu()
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}