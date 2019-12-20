package com.ageone.zenit.Modules.Chat.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import yummypets.com.stevia.*

class ChatMessageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {
    
    val textViewName by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 9F
        textView.textColor = Color.parseColor("#A4A4A4")
        textView.setBackgroundColor(Color.TRANSPARENT)
    	textView.setLines(1)
    // 	textView.elevation = 5F.dp
        textView
    }
    
    val viewBackMessage by lazy {
        val view = BaseView()
        view.cornerRadius = 15.dp
        view.backgroundColor = Color.parseColor("#E4E4E4")
        view.initialize()
        view
    }
    
    val textViewTime by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 9F
        textView.textColor = Color.parseColor("#A4A4A4")
        textView.setBackgroundColor(Color.TRANSPARENT)
    	textView.setLines(1)
    // 	textView.elevation = 5F.dp
        textView
    }


    init {

        renderUI()
    }

}

fun ChatMessageViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewName,
        viewBackMessage,
        textViewTime
    )


}

fun ChatMessageViewHolder.initialize() {

}
