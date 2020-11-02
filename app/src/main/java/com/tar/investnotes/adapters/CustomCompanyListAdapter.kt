package com.tar.investnotes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.tar.investnotes.R
import com.tar.investnotes.database.models.IndexR
import com.tar.investnotes.utils.Fonts

class CustomCompanyListAdapter(private var list: List<IndexR>, private val editText: EditText, private val context: Context,
                               private val listener: OnCompanyClick) : BaseAdapter(), ListAdapter {

    private var item: String = ""

    override fun getCount(): Int {
        return list.size
    }

    fun setList(list: List<IndexR>) {
        this.list = list
    }

    fun getItem(): String {
        return item
    }

    override fun getItem(pos: Int): IndexR {
        return list[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.holder_index_list, null)
        }
        val tvContact = view?.findViewById<View>(R.id.tvName) as TextView
        val tvDesc = view?.findViewById<View>(R.id.tvDesc) as TextView
        val name = "(${list[position].code}) ${list[position].shortName}"
        tvContact.text = name
        tvContact.typeface = Fonts.getKallisto()

        if(list[position].desc != "") {
            tvDesc.visibility = View.VISIBLE
            tvDesc.text = list[position].desc
            tvDesc.typeface = Fonts.getKallisto()
        } else {
            tvDesc.visibility = View.GONE
        }
        val cont = view.findViewById<View>(R.id.holderCont) as LinearLayout

        cont.setOnClickListener {
            run {
                listener.onItemClicked(getItem(position))
            }
        }
        return view
    }

    interface OnCompanyClick {
        fun onItemClicked(data: IndexR)
    }
}