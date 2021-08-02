package com.tar.investnotes.fragments

import android.util.Log
import com.tar.investnotes.R
import com.tar.investnotes.activities.MainActivity.Companion.TAG
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : SmartFragment(R.layout.fragment_history) {

    override fun onReady() {
        initViews()
        MainFragment.enableSideMenu()
        showMenu()
//        btn_next.setOnClickListener { onNextButtonClick() }

    }

    fun initViews() {
//        et_temp.typeface = Fonts.getFuturaPtMedium()
//        btn_next.typeface = Fonts.getFuturaPtBook()
//        btn_next.transformationMethod = null
        sumLabel.typeface = Fonts.getKallisto()
        cont_temp_fragment.startAnimation(Anim.getAppear(context))
        sumLabel.startAnimation(Anim.getAppearSlide(context, 200))
    }

    fun onNextButtonClick() {
        Log.d(TAG, "onNextButtonClick: ")
        showMenu()
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}