package com.tar.investnotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tar.investnotes.R
import com.tar.investnotes.activities.MainActivity
import com.tar.investnotes.database.models.InvestmentR
import com.tar.investnotes.utils.Fonts

class StockGroupAdapter(private val stockList: List<List<InvestmentR>>, private val sortBy: String, val activity: MainActivity) :
    RecyclerView.Adapter<StockGroupAdapter.StockGroupHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockGroupHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.holder_investment_group, parent, false)
        return StockGroupHolder(v)
    }

    override fun onBindViewHolder(holder: StockGroupHolder, position: Int) {
        var name = ""

        when (sortBy) {
            "all" -> {
                name = activity.getString(R.string.all)
            }
            "owner" -> {
                name = stockList[position][0].owner
            }
            "broker" -> {
                name = stockList[position][0].broker
            }
            "type" -> {
                name = stockList[position][0].type
            }
        }

        holder.bindItems(stockList[position], name, activity)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    class StockGroupHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(investments: List<InvestmentR>, name: String, activity: MainActivity) {
            val groupTitle = itemView.findViewById(R.id.groupTitle) as TextView
            val groupClose = itemView.findViewById(R.id.groupClose) as ImageView
            val stockRecyclerView = itemView.findViewById(R.id.stockRecyclerView) as RecyclerView

            groupTitle.typeface = Fonts.getKallisto()
            groupTitle.text = name
            groupClose.setOnClickListener {
                if(stockRecyclerView.isVisible) {
                    stockRecyclerView.visibility = View.GONE
                    groupClose.setImageResource(R.drawable.plus_white)
                } else {
                    stockRecyclerView.visibility = View.VISIBLE
                    groupClose.setImageResource(R.drawable.minus_white)
                }
            }
            stockRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val adapter = StockAdapter(investments, activity)
            stockRecyclerView.adapter = adapter

        }
    }

}