package com.tar.investnotes.presenters

import android.view.View
import com.tar.investnotes.activities.MainActivity
import com.tar.investnotes.adapters.CustomListAdapter
import com.tar.investnotes.fragments.StockFragment

class StockPresenter(private val activity: MainActivity, private val fragment: StockFragment) : CustomListAdapter.OnItemClick {

    override fun onItemClicked(data: String, view: View) {

    }
}