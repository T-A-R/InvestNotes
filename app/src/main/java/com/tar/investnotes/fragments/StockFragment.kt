package com.tar.investnotes.fragments

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tar.investnotes.R
import com.tar.investnotes.R2.attr.buttonStyle
import com.tar.investnotes.adapters.StockAdapter
import com.tar.investnotes.database.models.InvestmentR
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_stock.*
import kotlinx.android.synthetic.main.fragment_stock.cont_temp_fragment
import kotlinx.android.synthetic.main.fragment_stock.labelText

class StockFragment : SmartFragment(R.layout.fragment_stock) {

    private var investList: List<InvestmentR>? = null

    override fun onReady() {
        investList = getDao().getAllInvestments()

        initViews()
        MainFragment.enableSideMenu()
        showMenu()

    }

    private fun initViews() {
        labelText.typeface = Fonts.getKallisto()
        profitLabel.typeface = Fonts.getKallisto()
        labelTotal.typeface = Fonts.getKallisto()
        btnAll.typeface = Fonts.getKallisto()
        btnOwnerFilter.typeface = Fonts.getKallisto()
        btnBrokerFilter.typeface = Fonts.getKallisto()
        btnTypeFilter.typeface = Fonts.getKallisto()
        btnUSD.typeface = Fonts.getKallisto()
        btnEURO.typeface = Fonts.getKallisto()
        btnRUR.typeface = Fonts.getKallisto()
        btnCN.typeface = Fonts.getKallisto()
        btnGBP.typeface = Fonts.getKallisto()
        btnIND.typeface = Fonts.getKallisto()
        btnCN.typeface = Fonts.getKallisto()
        btnBTN.typeface = Fonts.getKallisto()
        profitMarker.setImageResource(R.drawable.arrow_up)
        cont_temp_fragment.startAnimation(Anim.getAppear(context))
        labelText.startAnimation(Anim.getAppearSlide(context, 200))
        profitMarker.startAnimation(Anim.getAppearSlide(context, 200))
        profitLabel.startAnimation(Anim.getAppearSlide(context, 200))
        stockFilter.startAnimation(Anim.getAppearSlide(context, 200))

        labelText.setOnClickListener { pressCurrency() }
        stockFilter.setOnClickListener { pressFilter() }
        btnAll.setOnClickListener { pressAllFilter() }
        btnOwnerFilter.setOnClickListener { pressOwnerFilter() }
        btnBrokerFilter.setOnClickListener { pressBrokerFilter() }
        btnTypeFilter.setOnClickListener { pressTypeFilter() }

        if (!investList.isNullOrEmpty()) {
            stockRecyclerView.layoutManager = LinearLayoutManager(getMainActivity(), RecyclerView.VERTICAL, false)
            val adapter = StockAdapter(investList!!, getMainActivity())
            stockRecyclerView.adapter = adapter
        }
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    private fun pressFilter() {
        if (filterCont.isVisible) {
            setContGone(filterCont)
        } else {
            setContVisible(filterCont)
            filterCont.bringToFront()
        }
    }

    private fun pressCurrency() {
        if (currencyCont.isVisible) {
            setContGone(currencyCont)
        } else {
            setContVisible(currencyCont)
            currencyCont.bringToFront()
        }
    }

    private fun pressAllFilter() {
        setButtonBackground(btnAll, true)
        setButtonBackground(btnOwnerFilter, false)
        setButtonBackground(btnBrokerFilter, false)
        setButtonBackground(btnTypeFilter, false)
        setContGone(filterCont)
    }

    private fun pressOwnerFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, true)
        setButtonBackground(btnBrokerFilter, false)
        setButtonBackground(btnTypeFilter, false)
        setContGone(filterCont)
    }

    private fun pressBrokerFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, false)
        setButtonBackground(btnBrokerFilter, true)
        setButtonBackground(btnTypeFilter, false)
        setContGone(filterCont)
    }

    private fun pressTypeFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, false)
        setButtonBackground(btnBrokerFilter, false)
        setButtonBackground(btnTypeFilter, true)
        setContGone(filterCont)
    }

    fun tests() {

//        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)
//        GlobalScope.launch {
//            println("============= STOCK ME: " + getMainActivity().apiClient.etfsCountryExposure("AAPL").toString())
//        }

//        MyAPI.getStockInfo(Constants.Default.API_ME_INDEX_URL + "AAPL" + Constants.Default.API_ME_INDEX_PARAMS, this)
    }
}