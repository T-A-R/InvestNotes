package com.tar.investnotes.fragments

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tar.investnotes.R
import com.tar.investnotes.activities.MainActivity.Companion.TAG
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import kotlinx.android.synthetic.main.fragment_temp.*


class AuthFragment : SmartFragment(R.layout.fragment_auth) {

    override fun onReady() {
        initViews()
        MainFragment.disableSideMenu()

        btn_next.setOnClickListener { onNextButtonClick() }

    }

    fun initViews() {
        et_temp.typeface = Fonts.getFuturaPtMedium()
        btn_next.typeface = Fonts.getFuturaPtBook()
        btn_next.transformationMethod = null
        cont_temp_fragment.startAnimation(Anim.getAppear(context))
        btn_next.startAnimation(Anim.getAppearSlide(context, 500))
    }

    private fun onNextButtonClick() {
        Log.d(TAG, "onNextButtonClick: ")
        replaceFragment(BankFragment())
    }

    override fun onBackPressed(): Boolean {
        return true
    }

//    override fun onResume() {
//        super.onResume()
//        (activity as AppCompatActivity?)?.supportActionBar?.hide()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        (activity as AppCompatActivity?)?.supportActionBar?.show()
//    }
}