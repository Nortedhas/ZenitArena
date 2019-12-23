package com.ageone.zenit.Modules.EventReg.rows

import android.graphics.Color
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.core.view.marginLeft
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.RadioButton.BaseRadioButton
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import timber.log.Timber
import yummypets.com.stevia.*

class EventRegRadioButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val radioButtonGroup by lazy {
        val radioGroup = RadioGroup(currentActivity)
        radioGroup
    }

    val radioGroupLayoutParams = RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)

    val radioButtonFirstExchange by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }

    val radioButtonSecondExchange by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }

    val radioButtonList = mutableListOf<RadioButton>()

    init {
        radioButtonList.add(radioButtonFirstExchange)
        radioButtonList.add(radioButtonSecondExchange)
        radioGroupLayoutParams.setMargins(0,16.dp,0,16.dp)

        renderUI()
    }

}

fun EventRegRadioButtonViewHolder.renderUI() {
    radioButtonGroup.removeAllViews()
    constraintLayout.subviews(
        radioButtonGroup
    )

    radioButtonGroup
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally(32)

}

fun EventRegRadioButtonViewHolder.initialize(variants: Array<String>) {
    for(i in 0 until radioButtonList.size) {
        radioButtonList[i].setPadding(25.dp,0,0,0)
        radioButtonList[i].textSize = 16F
        radioButtonList[i].textColor = Color.parseColor("#787880")
        radioButtonList[i].layoutParams = radioGroupLayoutParams
        radioButtonList[i].text = variants[i]
        radioButtonGroup.addView(radioButtonList[i])
        radioButtonList[i].setOnClickListener {
            Timber.i("RadioButton text :${radioButtonList[i].text}")
        }
    }

}
