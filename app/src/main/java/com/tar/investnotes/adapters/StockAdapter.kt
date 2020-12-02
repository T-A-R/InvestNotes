package com.tar.investnotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tar.investnotes.R
import com.tar.investnotes.database.models.InvestmentR

class StockAdapter(val stockList: List<InvestmentR>) : RecyclerView.Adapter<StockAdapter.StockHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.holder_investment, parent, false)
        return StockHolder(v)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.bindItems(stockList[position])
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    class StockHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(investment: InvestmentR) {
            val investLabel = itemView.findViewById(R.id.investLabel) as TextView
            val brokerLabel = itemView.findViewById(R.id.brokerLabel) as TextView
            val ownerLabel = itemView.findViewById(R.id.ownerLabel) as TextView
            val costLabel = itemView.findViewById(R.id.costLabel) as TextView
            val quantityLabel = itemView.findViewById(R.id.quantityLabel) as TextView
            val profitLabel = itemView.findViewById(R.id.profitLabel) as TextView
            val profitMarker = itemView.findViewById(R.id.profitMarker) as ImageView

            investLabel.text = investment.code
            brokerLabel.text = investment.broker
            ownerLabel.text = investment.owner
            costLabel.text = investment.priceBuy.toString()
            quantityLabel.text = investment.quantity.toString()
//            profitLabel.text =
            profitMarker.setImageResource(R.drawable.arrow_up)
        }
    }

}