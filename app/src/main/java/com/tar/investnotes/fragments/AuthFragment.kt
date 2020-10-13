package com.tar.investnotes.fragments

import android.util.Log
import com.tar.investnotes.R
import com.tar.investnotes.activities.MainActivity.Companion.TAG
import com.tar.investnotes.database.models.UserR
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_temp.*


class AuthFragment : SmartFragment(R.layout.fragment_auth) {

    private val disposables = CompositeDisposable()

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


}