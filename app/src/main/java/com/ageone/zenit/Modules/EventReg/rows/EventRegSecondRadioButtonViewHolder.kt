package com.ageone.zenit.Modules.EventReg.rows

import android.graphics.Color
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.RadioButton.BaseRadioButton
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import timber.log.Timber
import yummypets.com.stevia.*

class EventRegSecondRadioButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val radioButtonGroup by lazy {
        val radioGroup = RadioGroup(currentActivity)
        radioGroup
    }

    val radioGroupLayoutParams = RadioGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)

    val radioButtonInfo by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }

    val radioButtonTurnstile by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }

    val radioButtonSafeCamera by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }

    val radioButtonTicket by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }

    val radioButtonVip by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }

    val radioButtonEcology by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }
    val radioButtonFun by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }
    val radioButtonQuality by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }
    val radioButtonCeremony by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }
    val radioButtonPhoto by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }
    val radioButtonList = mutableListOf<RadioButton>()

    init {
        radioButtonList.add(radioButtonInfo)
        radioButtonList.add(radioButtonTurnstile)
        radioButtonList.add(radioButtonSafeCamera)
        radioButtonList.add(radioButtonTicket)
        radioButtonList.add(radioButtonVip)
        radioButtonList.add(radioButtonEcology)
        radioButtonList.add(radioButtonFun)
        radioButtonList.add(radioButtonQuality)
        radioButtonList.add(radioButtonCeremony)
        radioButtonList.add(radioButtonPhoto)
        radioGroupLayoutParams.setMargins(0,16.dp,0,16.dp)

        renderUI()
    }

}

fun EventRegSecondRadioButtonViewHolder.renderUI() {
    radioButtonGroup.removeAllViews()
    constraintLayout.subviews(
        radioButtonGroup
    )
    radioButtonGroup
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally(32)
}

fun EventRegSecondRadioButtonViewHolder.initialize(variants: Array<String>) {
    for(i in 0 until radioButtonList.size) {
        radioButtonList[i].text = "Test text"
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
