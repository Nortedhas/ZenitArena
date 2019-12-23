package com.ageone.zenit.Modules.EventItem.rows

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Button.BaseButton
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import com.ageone.zenit.R
import yummypets.com.stevia.*

class EventItemButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val buttonEventItem by lazy {
        val button = BaseButton()
        button.backgroundColor = Color.parseColor("#00ACEB")
        button.textColor = Color.WHITE
        button.textSize = 20F
        button.cornerRadius = 6.dp
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.text = "Зарегистрироваться"
        button.initialize()
        button
    }

    val viewEventItemTiming by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#00ACEB")
        view.cornerRadius = 8.dp
        view.initialize()
        view
    }

    val textViewEventItemTiming by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.textColor = Color.WHITE
        textView.text = "Тайминг"
        textView.initialize()
        textView
    }

    val imageViewEventItemTiming by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.setImageResource(R.drawable.ic_timing)
        imageView.initialize()
        imageView
    }

    val viewEventItemMap by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#00ACEB")
        view.cornerRadius = 8.dp
        view.initialize()
        view
    }

    val textViewEventItemMap by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.textColor = Color.WHITE
        textView.text = "Как добраться"
        textView.initialize()
        textView
    }

    val imageViewEventItemMap by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.setImageResource(R.drawable.ic_map)
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun EventItemButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonEventItem,
        viewEventItemTiming.subviews(
            textViewEventItemTiming,
            imageViewEventItemTiming
        ),
        viewEventItemMap.subviews(
            textViewEventItemMap,
            imageViewEventItemMap
        )
    )

    buttonEventItem
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(16)
        .height(50)

    viewEventItemTiming
        .constrainTopToBottomOf(buttonEventItem,16)
        .fillHorizontally(16)
        .height(50)

    textViewEventItemTiming
        .constrainCenterYToCenterYOf(viewEventItemTiming)
        .constrainCenterXToCenterXOf(viewEventItemTiming)

    imageViewEventItemTiming
        .constrainLeftToLeftOf(viewEventItemTiming,50)
        .constrainCenterYToCenterYOf(viewEventItemTiming)
        .height(27)
        .width(27)

    viewEventItemMap
        .constrainTopToBottomOf(viewEventItemTiming, 16)
        .fillHorizontally(16)
        .height(50)

    textViewEventItemMap
        .constrainCenterYToCenterYOf(viewEventItemMap)
        .constrainCenterXToCenterXOf(viewEventItemMap)

    imageViewEventItemMap
        .constrainLeftToLeftOf(viewEventItemMap,50)
        .constrainCenterYToCenterYOf(viewEventItemMap)
        .height(27)
        .width(27)

}

fun EventItemButtonViewHolder.initialize() {

}
