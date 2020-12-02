package com.tar.investnotes.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tar.investnotes.R
import com.tar.investnotes.activities.MainActivity
import com.tar.investnotes.database.models.InvestmentR
import com.tar.investnotes.utils.Fonts
import kotlin.math.roundToInt

class StockAdapter(val stockList: List<InvestmentR>, public val activity: MainActivity) : RecyclerView.Adapter<StockAdapter.StockHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.holder_investment, parent, false)
        return StockHolder(v)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        val name = activity.getDao().getIndex(stockList[position].code)?.name ?: ""
        holder.bindItems(stockList[position], name)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    class StockHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindItems(investment: InvestmentR, name: String) {
            val investLabel = itemView.findViewById(R.id.investLabel) as TextView
            val brokerLabel = itemView.findViewById(R.id.brokerLabel) as TextView
            val ownerLabel = itemView.findViewById(R.id.ownerLabel) as TextView
            val costLabel = itemView.findViewById(R.id.costLabel) as TextView
            val quantityLabel = itemView.findViewById(R.id.quantityLabel) as TextView
            val profitLabel = itemView.findViewById(R.id.profitLabel) as TextView
            val profitMarker = itemView.findViewById(R.id.profitMarker) as ImageView

            investLabel.typeface = Fonts.getKallisto()
            brokerLabel.typeface = Fonts.getKallisto()
            ownerLabel.typeface = Fonts.getKallisto()
            costLabel.typeface = Fonts.getKallisto()
            quantityLabel.typeface = Fonts.getKallisto()
            profitLabel.typeface = Fonts.getKallisto()

            val cost = "${(investment.priceBuy * investment.quantity + investment.commission)} $"
            val buyPrice = investment.priceBuy * investment.quantity + investment.commission
//            val lastPrice: Float = if (investment.priceLast.equals(0F)) buyPrice else investment.priceLast
            val lastPrice = 600F
            val isPositive = lastPrice > buyPrice
            val delta = if (isPositive) lastPrice - buyPrice else buyPrice - lastPrice
            val profit = "$delta $ (${"%.2f".format(delta * 100 / buyPrice)}%)"

            investLabel.text =  name
            brokerLabel.text = "Broker: ${investment.broker}"
            ownerLabel.text = "Owner: ${investment.owner}"
            costLabel.text = cost
            quantityLabel.text = investment.quantity.toString()
            profitLabel.text = profit
            if (isPositive) {
                profitMarker.setImageResource(R.drawable.arrow_up)
                profitLabel.setTextColor(Color.parseColor("#03AC10"))
            } else {
                profitMarker.setImageResource(R.drawable.arrow_down)
                profitLabel.setTextColor(Color.parseColor("#CC2418"))

            }
        }
    }

}