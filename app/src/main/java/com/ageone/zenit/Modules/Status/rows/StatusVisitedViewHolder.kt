package com.ageone.zenit.Modules.Status.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import yummypets.com.stevia.*

class StatusVisitedViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#B3B3B5")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }
    
    val viewCircle by lazy {
        val view = BaseView()
        view.cornerRadius = 45.dp
        view.backgroundColor = Color.parseColor("#7EDEFF")
        view.initialize()
    // 	imageView.elevation = 5F.dp
        view
    }
    
    val textViewCount by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 22F
        textView.textColor = Color.parseColor("#FFFFFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }
    
    val textViewEvents by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 28F
        textView.textColor = Color.parseColor("#00ACEB")
        textView.setBackgroundColor(Color.TRANSPARENT)
    //	textView.setLines(1)
    // 	textView.elevation = 5F.dp
        textView
    }

    init {
        textView.text = "Вы посетили:"
        textViewEvents.text = "Мероприятий:"

        renderUI()
    }

}

fun StatusVisitedViewHolder.renderUI() {
    constraintLayout.subviews(
        textView,
        textViewEvents,
        viewCircle.subviews(
            textViewCount
        )
    )

    textView
        .constrainTopToTopOf(constraintLayout, 8)
        .fillHorizontally()
        .constrainLeftToLeftOf(constraintLayout, 32)

    textViewEvents
        .constrainTopToBottomOf(textView, 16)
//        .fillHorizontally()
        .constrainLeftToLeftOf(constraintLayout, 32)

    viewCircle
        .height(45)
        .width(45)
        .constrainLeftToRightOf(textViewEvents, 16)
        .constrainCenterYToCenterYOf(textViewEvents)


    textViewCount
        .fillHorizontally()
        .fillVertically()

}

fun StatusVisitedViewHolder.initialize(count: Int) {
    textViewCount.text = "$count"
}
