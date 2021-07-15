package com.tar.investnotes.fragments

import androidx.core.view.isVisible
import com.tar.investnotes.Constants
import com.tar.investnotes.R
import com.tar.investnotes.presenters.StockPresenter
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import kotlinx.android.synthetic.main.fragment_stock.*
import kotlinx.android.synthetic.main.fragment_stock.cont_temp_fragment
import kotlinx.android.synthetic.main.fragment_stock.labelText

class StockFragment : SmartFragment(R.layout.fragment_stock) {

    private lateinit var presenter: StockPresenter

    override fun onReady() {
        presenter = StockPresenter(getMainActivity(), this)

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

        presenter.setAdapter()
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
        presenter.sortBy = Constants.Sort.ALL
        presenter.setAdapter()
    }

    private fun pressOwnerFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, true)
        setButtonBackground(btnBrokerFilter, false)
        setButtonBackground(btnTypeFilter, false)
        setContGone(filterCont)
        presenter.sortBy = Constants.Sort.OWNER
        presenter.setAdapter()
    }

    private fun pressBrokerFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, false)
        setButtonBackground(btnBrokerFilter, true)
        setButtonBackground(btnTypeFilter, false)
        setContGone(filterCont)
        presenter.sortBy = Constants.Sort.BROKER
        presenter.setAdapter()
    }

    private fun pressTypeFilter() {
        setButtonBackground(btnAll, false)
        setButtonBackground(btnOwnerFilter, false)
        setButtonBackground(btnBrokerFilter, false)
        setButtonBackground(btnTypeFilter, true)
        setContGone(filterCont)
        presenter.sortBy = Constants.Sort.TYPE
        presenter.setAdapter()
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