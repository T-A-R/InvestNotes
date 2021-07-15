package com.tar.investnotes.presenters

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tar.investnotes.Constants
import com.tar.investnotes.activities.MainActivity
import com.tar.investnotes.adapters.CustomListAdapter
import com.tar.investnotes.adapters.StockGroupAdapter
import com.tar.investnotes.database.models.InvestmentR
import com.tar.investnotes.fragments.StockFragment
import kotlinx.android.synthetic.main.fragment_stock.*

class StockPresenter(private val activity: MainActivity, private val fragment: StockFragment) : CustomListAdapter.OnItemClick {

    private val investList: List<InvestmentR>? = activity.getDao().getAllInvestments()
    var sortBy = Constants.Sort.ALL
    private lateinit var adapter: StockGroupAdapter

    fun setAdapter() {
        if (!investList.isNullOrEmpty()) {
            fragment.stockRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val investments = mutableListOf<List<InvestmentR>>()
            var map: Map<String, List<InvestmentR>>
            when (sortBy) {
                Constants.Sort.ALL -> {
                    investments.add(investList)
                }
                Constants.Sort.OWNER -> {
                    map = investList.groupBy { it.owner }
                    map.forEach {
                        investments.add(it.value)
                    }
                }
                Constants.Sort.BROKER -> {
                    map = investList.groupBy { it.broker }
                    map.forEach {
                        investments.add(it.value)
                    }
                }
                Constants.Sort.TYPE -> {
                    map = investList.groupBy { it.type }
                    map.forEach {
                        investments.add(it.value)
                    }
                }
            }

            adapter = StockGroupAdapter(investments, sortBy, activity)
            fragment.stockRecyclerView.adapter = adapter
        }
    }

    override fun onItemClicked(data: String, view: View) {

    }
}