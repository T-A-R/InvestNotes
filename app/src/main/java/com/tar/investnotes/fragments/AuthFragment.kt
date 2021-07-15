package com.tar.investnotes.fragments

//import javax.security.auth.callback.Callback

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import com.tar.investnotes.Constants
import com.tar.investnotes.CoreApp
import com.tar.investnotes.R
import com.tar.investnotes.activities.MainActivity.Companion.TAG
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_temp.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yahoofinance.YahooFinance
import java.io.BufferedReader


class AuthFragment : SmartFragment(R.layout.fragment_auth) {

    private val disposables = CompositeDisposable()

    override fun onReady() {
        initViews()
        MainFragment.disableSideMenu()

        btn_next.setOnClickListener { onNextButtonClick() }

    }

    fun initViews() {
        et_temp.typeface = Fonts.getKallisto()
        btn_next.typeface = Fonts.getKallisto()
//        btn_next.transformationMethod = null
        cont_temp_fragment.startAnimation(Anim.getAppear(context))
        btn_next.startAnimation(Anim.getAppearSlide(context, 500))
    }

    private fun onNextButtonClick() {
        Log.d(TAG, "onNextButtonClick: ")
        replaceFragment(StockFragment())

//        getDao().insertUser(UserR())
//
//        val subscribe = Observable.fromCallable {
//            try {
//                getDao().getUsers()
//            } catch (e: Exception) {
//                throw RuntimeException(e);
//            }
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { list: List<UserR>? -> et_temp.hint = list?.get(0)?.user_type ?: "nope" },
//                { error: Throwable? -> error?.printStackTrace() })
//
//        disposables.add(subscribe)

    }

    override fun onBackPressed(): Boolean {
        return true
    }

    override fun onStop() {
        super.onStop()
        disposables.dispose()
    }

//    fun getIndex(index: String) {
//        GlobalScope.launch {
//            val stock = YahooFinance.get(index)
//            stock?.let {
//                stock.print()
//                val price = stock.getQuote(true).price
//                val name = stock.name
//                if (name != null) {
//                    showToast("$name ${price?.toPlainString() ?: "-"}")
//                } else {
//                    showToast("Не найден")
//                }
//            }
//        }
//    }
}