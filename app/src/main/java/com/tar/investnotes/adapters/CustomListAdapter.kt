package com.tar.investnotes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import com.tar.investnotes.R
import java.util.*

class CustomListAdapter(private val list: ArrayList<String>, private val context: Context) : BaseAdapter(), ListAdapter {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(pos: Int): Any {
        return list[pos]
    }

    override fun getItemId(pos: Int): Long {
//            return list.get(pos).getId();
        return 0
        //just return 0 if your list items do not have an Id variable.
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.holder_custom_list, null)
        }
        val tvContact = view.findViewById<View>(R.id.tvElement) as TextView
        tvContact.text = list[position]
        val cont = view.findViewById<View>(R.id.holderCont) as RelativeLayout
        cont.setOnClickListener { v: View? -> }
        return view
    }
}