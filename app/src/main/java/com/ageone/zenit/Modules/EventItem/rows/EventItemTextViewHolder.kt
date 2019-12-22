package com.ageone.zenit.Modules.EventItem.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*

class EventItemTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewDate by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#747480")
        textView.initialize()
        textView
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.textColor = Color.BLACK
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.initialize()
        textView
    }

    val textViewEventItem by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView.typeface = Typeface.DEFAULT
        textView.initialize()
        textView
    }

    val format = SimpleDateFormat("dd MMMM, yyyy", Locale("ru"))


    init {

        renderUI()
    }

}

fun EventItemTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewDate,
        textViewTitle,
        textViewEventItem
    )

    textViewDate
        .constrainTopToTopOf(constraintLayout,8)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewTitle
        .constrainTopToBottomOf(textViewDate,4)
        .fillHorizontally(16)

    textViewEventItem
        .constrainTopToBottomOf(textViewTitle,4)
        .fillHorizontally(16)
}

fun EventItemTextViewHolder.initialize(date: Int, title: String, text: String) {

    textViewDate.text = format.format(Date(date.toLong() * 1000))
    textViewTitle.text = title
    textViewEventItem.text = text



}
