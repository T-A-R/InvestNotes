package com.tar.investnotes.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.tar.investnotes.R
import com.tar.investnotes.adapters.CustomListAdapter
import com.tar.investnotes.presenters.AddPresenter
import com.tar.investnotes.utils.Anim
import com.tar.investnotes.utils.Fonts
import kotlinx.android.synthetic.main.fragment_add.*
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : SmartFragment(R.layout.fragment_add) {

    private lateinit var presenter: AddPresenter

    override fun onReady() {
        presenter = AddPresenter(getMainActivity(), this)

        initViews()
        MainFragment.enableSideMenu()
        showMenu()

        startAddItemProcess()
    }

    private fun initViews() {
        labelText.typeface = Fonts.getKallisto()
        ownerLabel.typeface = Fonts.getKallisto()
        ownerText.typeface = Fonts.getKallisto()
        typeLabel.typeface = Fonts.getKallisto()
        typeText.typeface = Fonts.getKallisto()
        brokerLabel.typeface = Fonts.getKallisto()
        brokerText.typeface = Fonts.getKallisto()
        investNameLabel.typeface = Fonts.getKallisto()
        investNameText.typeface = Fonts.getKallisto()
        priceLabel.typeface = Fonts.getKallisto()
        priceText.typeface = Fonts.getKallisto()
        quantityLabel.typeface = Fonts.getKallisto()
        quantityText.typeface = Fonts.getKallisto()
        commissionLabel.typeface = Fonts.getKallisto()
        commissionText.typeface = Fonts.getKallisto()
        totalLabel.typeface = Fonts.getKallisto()
        totalText.typeface = Fonts.getKallisto()
        dateLabel.typeface = Fonts.getKallisto()
        dateText.typeface = Fonts.getKallisto()
        btnAdd.typeface = Fonts.getKallisto()
        cont_temp_fragment.startAnimation(Anim.getAppear(context))
        labelText.startAnimation(Anim.getAppearSlide(context, 200))

        btnAdd.setOnClickListener { onAddButtonClick() }
        btnReloadOwner.setOnClickListener { setOwner() }
        btnReloadType.setOnClickListener { setType() }
        btnReloadBroker.setOnClickListener { setBroker() }
        btnReloadInvestName.setOnClickListener { setInvestment() }
        btnReloadPrice.setOnClickListener { setPrice() }
        btnReloadQuantity.setOnClickListener { setQuantity() }
        btnReloadCommission.setOnClickListener { setCommission() }
        btnReloadDate.setOnClickListener { setDate() }
    }

    private fun startAddItemProcess() {
        setOwner()
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    private fun showInputDialog(mLabel: String, textView: TextView, list: List<String>?) {
        val layoutInflaterAndroid = LayoutInflater.from(activity)
        val mView: View = layoutInflaterAndroid.inflate(R.layout.dialog_input_list, null)
        val dialog = AlertDialog.Builder(activity, R.style.AlertDialogTheme)
        dialog.setView(mView)

        val mLabelTv = mView.findViewById(R.id.dialogListLabel) as TextView
        val mValueEt = mView.findViewById(R.id.dialogEditText) as EditText
        val mCancelBtn = mView.findViewById(R.id.btnCancel) as Button
        val mSetBtn = mView.findViewById(R.id.btnSet) as Button
        val mListView = mView.findViewById(R.id.listView) as ListView

        dialog.setCancelable(false)
        val alertDialog = dialog.create()

        list?.let {
//            val adapter = CustomListAdapter(list, mValueEt, getMainActivity())
            presenter.setDialog(alertDialog)
            mListView.adapter = presenter.getListAdapter(textView, list)
            mListView.visibility = if (list.isNotEmpty()) View.VISIBLE else View.GONE

        }

        mLabelTv.text = mLabel
        mLabelTv.typeface = Fonts.getKallisto()
        mValueEt.typeface = Fonts.getKallisto()
        mCancelBtn.typeface = Fonts.getKallisto()
        mSetBtn.typeface = Fonts.getKallisto()
        mCancelBtn.setOnClickListener {
            run {
                if (!getMainActivity().isFinishing) alertDialog.dismiss()
                presenter.onCancelPress(textView)
            }
        }
        mSetBtn.setOnClickListener {
            run {
                if (mValueEt.text.isNotEmpty()) {
                    presenter.stopSearchIndex()
                    if (!getMainActivity().isFinishing) alertDialog.dismiss()
                    presenter.setValue(textView, mValueEt.text.toString())
                } else {
                    showToast(getString(R.string.please_enter_value))
                }
            }
        }

        mValueEt.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (mValueEt.text.isNotEmpty()) {
                    presenter.stopSearchIndex()
                    if (!getMainActivity().isFinishing) alertDialog.dismiss()
                    presenter.setValue(textView, mValueEt.text.toString())
                    handled = true
                } else {
                    showToast(getString(R.string.please_enter_value))
                }

            }
            handled
        }

        when (textView) {
            investNameText -> {
                mValueEt.hint = getString(R.string.enter_code_here)
                presenter.setDialog(alertDialog)
                presenter.findIndex(mValueEt, mListView)
            }
            priceText, commissionText -> {
                mValueEt.inputType = InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_DECIMAL
            }
            quantityText -> {
                mValueEt.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }

        if (!getMainActivity().isFinishing) alertDialog.show()

    }

    private fun onAddButtonClick() {
        if (presenter.addInvestment()) replaceFragment(StockFragment())
        else {
            when (presenter.counter) {
                0 -> setOwner()
                1 -> setType()
                2 -> setBroker()
                3 -> setInvestment()
                4 -> setPrice()
                5 -> setQuantity()
                6 -> setCommission()
            }
        }
    }

    private fun setOwner() {
        setContInvisible(ownerCont)
        showInputDialog("Enter Owner of Investment", ownerText, presenter.getAllOwners())
    }

    fun setType() {
        setContInvisible(typeCont)
        showInputDialog("Enter Type of Investment", typeText, presenter.getInvestTypes())
    }

    fun setBroker() {
        setContInvisible(brokerCont)
        showInputDialog("Enter Broker", brokerText, presenter.getBrokers())
    }

    fun setInvestment() {
        setContInvisible(investNameCont)
        showInputDialog("Enter Code or Name of Investment", investNameText, null)
    }

    fun setPrice() {
        setContInvisible(priceCont)
        showInputDialog("Enter Price of unit", priceText, null)
    }

    fun setQuantity() {
        setContInvisible(quantityCont)
        showInputDialog("Enter Quantity", quantityText, null)
    }

    fun setCommission() {
        setContInvisible(commissionCont)
        showInputDialog("Enter Commission", commissionText, null)
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDate() {
        val mCalendar = Calendar.getInstance()
        setContInvisible(dateCont)
        val dialog = DatePickerDialog(
            activity as Context, { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                mCalendar[Calendar.YEAR] = year
                mCalendar[Calendar.MONTH] = monthOfYear
                mCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                val dateFormat = SimpleDateFormat("dd.MM.yyyy")
                dateFormat.timeZone = mCalendar.timeZone
                dateText.text = dateFormat.format(mCalendar.time)
                presenter.setDate(mCalendar.timeInMillis / 1000)
            },
            mCalendar[Calendar.YEAR],
            mCalendar[Calendar.MONTH],
            mCalendar[Calendar.DAY_OF_MONTH]
        )
        dialog.setOnDismissListener { setContVisible(dateCont) }
        dialog.show()
    }


}