package com.ageone.zenit.Modules.Event.rows

import android.graphics.Color
import android.graphics.Outline
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewOutlineProvider
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.ConstraintLayout.setOnlyTopRoundedCorners
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*

class EventItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val shape = GradientDrawable()

    val viewBack by lazy {
        val view = BaseView()
        shape.cornerRadii = floatArrayOf(0F,0F,8F.dp,8F.dp,8F.dp,8F.dp,0F,0F)
        shape.setColor(Color.parseColor("#FAFBFF"))
        view.gradientDrawable = shape
        view.initialize()
        view
    }

    val viewLine by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.backgroundColor = Color.parseColor("#DDF4FC")
        view.initialize()
        view
    }

    val textViewEvent by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.typeface = Typeface.DEFAULT
        textView.textColor = Color.parseColor("#00B7FA")
        textView.initialize()
        textView
    }

    val format = SimpleDateFormat("dd.MM.yyyy, HH:MM", Locale("ru"))

    init {
        renderUI()
    }

}

fun EventItemViewHolder.renderUI() {
    constraintLayout.subviews(
        viewBack.subviews(
            viewLine,
            textViewEvent
        )
    )

    viewBack
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(16)
        .height(73)

    viewLine
        .constrainTopToTopOf(viewBack)
        .constrainBottomToBottomOf(viewBack)
        .constrainLeftToLeftOf(viewBack)
        .width(7)

    textViewEvent
        .constrainTopToTopOf(viewBack,16)
        .fillHorizontally(24)
}

fun EventItemViewHolder.initialize(date: Int,text: String) {
    val date = format.format(Date(date.toLong() * 1000))
    textViewEvent.text = "$date, $text"
}
