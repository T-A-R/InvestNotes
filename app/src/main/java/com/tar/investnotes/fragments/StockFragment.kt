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
import com.tar.investnotes.adapters.StockGroupAdapter
import com.tar.investnotes.database.models.InvestmentR
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_stock.*
import kotlinx.android.synthetic.main.fragment_stock.cont_temp_fragment
import kotlinx.android.synthetic.main.fragment_stock.labelText

class StockFragment : SmartFragment(R.layout.fragment_stock) {

    private var investList: List<InvestmentR>? = null
    private var sortBy: String = "all"
    lateinit var adapter: StockGroupAdapter

    override fun onReady() {
        investList = getDao().getAllInvestments()

        initViews()
        MainFragment.enableSideMenu()
        showMenu()

    }

    private fun initViews() {
        labelText.typeface = Fonts.getKallisto()
        profitLabel.typeface = Fonts.getKallisto()
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

        setAdapter()
    }

    fun setAdapter() {
        if (!investList.isNullOrEmpty()) {
            stockRecyclerView.layoutManager = LinearLayoutManager(getMainActivity(), RecyclerView.VERTICAL, false)
            val investments = mutableListOf<List<InvestmentR>>()
            var map: Map<String, List<InvestmentR>>
            when (sortBy) {
                "all" -> {
                    investments.add(investList!!)
                }
                "owner" -> {
                    map = investList!!.groupBy { it.owner }
                    map.forEach {
                        investments.add(it.value)
                    }
                }
                "broker" -> {
                    map = investList!!.groupBy { it.broker }
                    map.forEach {
                        investments.add(it.value)
                    }
                }
                "type" -> {
                    map = investList!!.groupBy { it.type }
                    map.forEach {
                        investments.add(it.value)
                    }
                }
            }

            adapter = StockGroupAdapter(investments, sortBy, getMainActivity())
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
            setContGone(currencyCont)
            setContVisible(filterCont)
            filterCont.bringToFront()
        }
    }

    private fun pressCurrency() {
        if (currencyCont.isVisible) {
            setContGone(currencyCont)
        } else {
            setContGone(filterCont)
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
        sortBy = "all"
        setAdapter()
//        adapter.notifyDataSetChanged()
    }

    private fun pressOwnerFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, true)
        setButtonBackground(btnBrokerFilter, false)
        setButtonBackground(btnTypeFilter, false)
        setContGone(filterCont)
        sortBy = "owner"
        setAdapter()
//        adapter.notifyDataSetChanged()
    }

    private fun pressBrokerFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, false)
        setButtonBackground(btnBrokerFilter, true)
        setButtonBackground(btnTypeFilter, false)
        setContGone(filterCont)
        sortBy = "broker"
        setAdapter()
//        adapter.notifyDataSetChanged()
    }

    private fun pressTypeFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, false)
        setButtonBackground(btnBrokerFilter, false)
        setButtonBackground(btnTypeFilter, true)
        setContGone(filterCont)
        sortBy = "type"
        setAdapter()
//        adapter.notifyDataSetChanged()
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