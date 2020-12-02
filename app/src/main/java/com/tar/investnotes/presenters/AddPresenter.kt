package com.tar.investnotes.presenters

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.app.AlertDialog
import android.widget.TextView
import com.github.underscore.lodash.U
import com.google.gson.GsonBuilder
import com.jakewharton.rxbinding2.widget.RxTextView
import com.tar.investnotes.Constants
import com.tar.investnotes.CoreApp
import com.tar.investnotes.R
import com.tar.investnotes.activities.MainActivity
import com.tar.investnotes.activities.MainActivity.Companion.TAG
import com.tar.investnotes.adapters.CustomCompanyListAdapter
import com.tar.investnotes.adapters.CustomListAdapter
import com.tar.investnotes.api.responses.index.IndexObject
import com.tar.investnotes.api.responses.index.Row
import com.tar.investnotes.database.models.*
import com.tar.investnotes.fragments.AddFragment
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.DateUtils
import com.tar.investnotes.utils.StringUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add.*
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit

class AddPresenter(private val activity: MainActivity, private val fragment: AddFragment) : CustomCompanyListAdapter.OnCompanyClick, CustomListAdapter.OnItemClick {

    private val investment = InvestmentR()
    private var index = IndexR()
    var counter: Int = 0
    private lateinit var alertDialog: AlertDialog

    private var disposableSearchIndex: Disposable? = null
    val mNamesList = mutableListOf<IndexR>()
    lateinit var adapter: CustomCompanyListAdapter

    fun getAllOwners(): List<String> {
        return activity.getDao().getOwners() ?: listOf()
    }

    fun getInvestTypes(): List<String> {
        return activity.getDao().getInvestTypes() ?: listOf()
    }

    fun getBrokers(): List<String> {
        return activity.getDao().getBrokers() ?: listOf()
    }

    fun addInvestment(): Boolean {
        return if (counter == 7) {
            val owner = OwnerR()
            owner.name = investment.owner
            val type = InvestTypeR()
            type.name = investment.type
            val broker = BrokerR()
            broker.name = investment.broker
            activity.getDao().insertOwner(owner)
            activity.getDao().insertInvestType(type)
            activity.getDao().insertBroker(broker)
            activity.getDao().insertIndex(index)
            activity.getDao().insertInvestment(investment)
            true
        } else {
            false
        }
    }

    fun setValue(view: View, text: String) {
        Log.d(TAG, "setValue:  $view / ${fragment.ownerText}")
        when (view) {
            fragment.ownerText -> {
                investment.owner = text
                fragment.ownerText.text = text
                fragment.labelText.visibility = View.GONE
                fragment.ownerCont.startAnimation(Anim.getEnterFromRight(activity))
                fragment.ownerCont.visibility = View.VISIBLE
                if (counter == 0) {
                    counter++
                    fragment.setType()
                }
            }
            fragment.typeText -> {
                investment.type = text
                fragment.typeText.text = text
                fragment.typeCont.startAnimation(Anim.getEnterFromRight(activity))
                fragment.typeCont.visibility = View.VISIBLE
                if (counter == 1) {
                    counter++
                    fragment.setBroker()
                }
            }
            fragment.brokerText -> {
                investment.broker = text
                fragment.brokerText.text = text
                fragment.brokerCont.startAnimation(Anim.getEnterFromRight(activity))
                fragment.brokerCont.visibility = View.VISIBLE
                if (counter == 2) {
                    counter++
                    fragment.setInvestment()
                }
            }
            fragment.investNameText -> {
                investment.code = text
                fragment.investNameText.text = text
                fragment.investNameCont.startAnimation(Anim.getEnterFromRight(activity))
                fragment.investNameCont.visibility = View.VISIBLE
                val data = IndexR()
                data.shortName = text
                stopSearchIndex()
                setIndex(data)
            }
            fragment.priceText -> {
                val priceFloat: Float = text.toFloat()
                val priceString = "$priceFloat ${investment.currency}"
                investment.priceBuy = priceFloat
                fragment.priceText.text = priceString
                fragment.priceCont.startAnimation(Anim.getEnterFromRight(activity))
                fragment.priceCont.visibility = View.VISIBLE
                if (counter == 4) {
                    counter++
                    fragment.setQuantity()
                }
            }
            fragment.quantityText -> {
                val quantity = text.toInt()
                investment.quantity = quantity
                fragment.quantityText.text = "$quantity"
                fragment.quantityCont.startAnimation(Anim.getEnterFromRight(activity))
                fragment.quantityCont.visibility = View.VISIBLE
                if (counter == 5) {
                    counter++
                    fragment.setCommission()
                }
            }
            fragment.commissionText -> {
                val commissionFloat: Float = text.toFloat()
                val commissionString = "$commissionFloat $"
                val totalFloat: Float = (investment.priceBuy * investment.quantity) + commissionFloat
                val totalString = "$totalFloat ${investment.currency}"
                val dateLong = DateUtils.currentTimeMillis
                investment.commission = commissionFloat
                fragment.commissionText.text = commissionString
                fragment.commissionCont.startAnimation(Anim.getEnterFromRight(activity))
                fragment.commissionCont.visibility = View.VISIBLE
                investment.date = dateLong
                fragment.dateText.text = DateUtils.getCurrentFormattedDate(DateUtils.PATTERN_DAY)
                fragment.dateCont.startAnimation(Anim.getEnterFromRight(activity, 200))
                fragment.dateCont.visibility = View.VISIBLE
                fragment.totalText.text = totalString
                fragment.totalCont.startAnimation(Anim.getEnterFromRight(activity, 400))
                fragment.totalCont.visibility = View.VISIBLE
                fragment.btnAdd.text = activity.getString(R.string.add)
                counter = 7
            }
        }
        Log.d(TAG, "setValue: END " + counter)
        if (counter == 7) fragment.btnAdd.text = activity.getString(R.string.add)
    }

    fun onCancelPress(view: TextView) {
        when (view) {
            fragment.ownerText -> if (view.text.isNotEmpty()) fragment.setContVisible(fragment.ownerCont)
            fragment.typeText -> if (view.text.isNotEmpty()) fragment.setContVisible(fragment.typeCont)
            fragment.brokerText -> if (view.text.isNotEmpty()) fragment.setContVisible(fragment.brokerCont)
            fragment.investNameText -> if (view.text.isNotEmpty()) fragment.setContVisible(fragment.investNameCont)
            fragment.priceText -> if (view.text.isNotEmpty()) fragment.setContVisible(fragment.priceCont)
            fragment.quantityText -> if (view.text.isNotEmpty()) fragment.setContVisible(fragment.quantityCont)
            fragment.commissionText -> if (view.text.isNotEmpty()) fragment.setContVisible(fragment.commissionCont)
            fragment.dateText -> if (view.text.isNotEmpty()) fragment.setContVisible(fragment.dateCont)
        }
    }

    private fun setIndex(indexR: IndexR) {
        index = indexR
        investment.code = index.code
        fragment.investNameText.text = indexR.shortName
        fragment.investNameCont.startAnimation(Anim.getEnterFromRight(activity))
        fragment.investNameCont.visibility = View.VISIBLE
        if (counter == 3) {
            counter++
            fragment.setPrice()
        }
    }

    fun setDate(date: Long) {
        investment.date = date
    }

    fun findIndex(editText: EditText, listView: ListView) {

        adapter = CustomCompanyListAdapter(mNamesList, editText, activity, this)
        listView.adapter = adapter

        disposableSearchIndex = RxTextView.textChangeEvents(editText)
            .debounce(400, TimeUnit.MILLISECONDS)
            .filter { changes -> StringUtils.isNotNullOrEmpty(changes.text().toString()) }
            .map { listener -> listener.text().toString() }
            .flatMap {
                return@flatMap CoreApp.retrofitAPI?.getStockInfo(Constants.Default.API_ME_INDEX_URL + it + Constants.Default.API_ME_INDEX_PARAMS)
                    ?.onErrorResumeNext(Observable.empty())
            }
            .map { body -> getStringIndex(body) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getSearchObserver(listView))
    }

    private fun getSearchObserver(listView: ListView): DisposableObserver<List<IndexR>>? {
        return object : DisposableObserver<List<IndexR>>() {
            override fun onComplete() {
                Log.d(TAG, "onComplete: ")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: ${e.message}")
            }

            override fun onNext(indexList: List<IndexR>) {
                listView.visibility = if (indexList.isNotEmpty()) View.VISIBLE else View.GONE

                adapter.setList(indexList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun getStringIndex(data: ResponseBody?): List<IndexR> {
        data?.let {
            val json: String = U.xmlToJson(data.string())

            Log.d(TAG, "?????? getStringIndex: $json")

            val serverAnswer1: IndexObject?

            try {
                serverAnswer1 = GsonBuilder().create().fromJson(json, IndexObject::class.java)
            } catch (pE: Exception) {

                pE.printStackTrace()
                return listOf()
            }

            val rows: List<Row>? = serverAnswer1?.document?.data?.rows?.row
            val indexList = mutableListOf<IndexR>()
            rows?.let {
                for (row in rows) {
                    val indexR = IndexR()
                    indexR.code = row.secid ?: ""
                    indexR.shortName = row.shortname ?: ""
                    indexR.name = row.name ?: ""
                    indexR.desc = row.emitent_title ?: ""
                    indexR.market = row.primary_boardid ?: ""

                    Log.d(TAG, "onGetStockInfo: ${indexR.name}")
                    if (indexR.name != "" && indexR.name != "null")
                        indexList.add(indexR)
                }
                return indexList
            }
        }
        return listOf()
    }

    fun stopSearchIndex() {
        disposableSearchIndex?.dispose()
    }

    fun setDialog(dialog: AlertDialog) {
        alertDialog = dialog
    }

    override fun onItemClicked(data: IndexR) {
        stopSearchIndex()
        setIndex(data)
        Log.d(TAG, "onItemClicked: ${data.shortName} (${data.code})")
        try {
            alertDialog.dismiss()
            Log.d(TAG, "onItemClicked: DISS")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getListAdapter(view: View, list: List<String>) : CustomListAdapter {
        return CustomListAdapter(list, view, activity, this)
    }

    override fun onItemClicked(data: String, view: View) {

        try {
            alertDialog.dismiss()
            Log.d(TAG, "onItemClicked: DISS")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        setValue(view, data)
    }
}