package com.tar.investnotes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.tar.investnotes.R
import com.tar.investnotes.database.models.IndexR
import com.tar.investnotes.utils.Fonts

class CustomListAdapter(private var list: List<String>, private val parentView: View, private val context: Context, private val listener: OnItemClick) : BaseAdapter(), ListAdapter {

    private var item: String = ""

    override fun getCount(): Int {
        return list.size
    }

    fun setList(list: List<String>) {
        this.list = list
    }

    fun getItem(): String {
        return item
    }

    override fun getItem(pos: Int): String {
        return list[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.holder_custom_list, null)
        }
        val tvContact = view?.findViewById<View>(R.id.tvElement) as TextView
        tvContact.text = list[position]
        tvContact.typeface = Fonts.getKallisto()
        val cont = view.findViewById<View>(R.id.holderCont) as LinearLayout

        val text = getItem(position)
        cont.setOnClickListener {
            run {
                item = text
//                editText.setText(text)
                listener.onItemClicked(text, parentView)
            }
        }
        return view
    }

    interface OnItemClick {
        fun onItemClicked(data: String, view : View)
    }
}