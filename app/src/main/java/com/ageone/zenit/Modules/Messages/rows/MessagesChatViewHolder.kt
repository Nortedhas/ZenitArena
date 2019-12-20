package com.ageone.zenit.Modules.Messages.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.R
import yummypets.com.stevia.*

class MessagesChatViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewIcon by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#000000")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val imageViewArrow by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }
    
    val viewBottomLine by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#DDDDDD")
        view.initialize()
        view
    }
    
    val viewRedCircle by lazy {
        val view = BaseView()
        view.cornerRadius = 13.dp
        view.backgroundColor = Color.parseColor("#FF0000")
        view.borderWidth = 2.dp
        view.borderColor = Color.WHITE
        view.initialize()
    // 	imageView.elevation = 5F.dp
        view
    }


    init {

        imageViewArrow.setBackgroundResource(R.drawable.ic_messages_arrow)

        renderUI()
    }

}

fun MessagesChatViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewIcon,
        textViewTitle,
        imageViewArrow,
        viewBottomLine,
        viewRedCircle
    )

    imageViewIcon
        .width(30)
        .height(30)
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainLeftToLeftOf(constraintLayout, 24)
        .constrainBottomToBottomOf(constraintLayout, 16)

    textViewTitle
        .constrainCenterYToCenterYOf(imageViewIcon)
        .constrainLeftToRightOf(imageViewIcon, 32)

    imageViewArrow
        .width(8)
        .height(14)
        .constrainCenterYToCenterYOf(imageViewIcon)
        .constrainRightToRightOf(constraintLayout, 16)

    viewBottomLine
        .height(1)
        .fillHorizontally(16)
        .constrainBottomToBottomOf(constraintLayout)

    viewRedCircle
        .width(13)
        .height(13)
        .constrainCenterYToBottomOf(imageViewIcon)
        .constrainCenterXToRightOf(imageViewIcon)
        .visibility = View.GONE

}

fun MessagesChatViewHolder.initialize(icon: Int, title: String, isLastElement: Boolean, isRedCircleVisible: Boolean) {
    imageViewIcon.setImageResource(icon)
    textViewTitle.text = title

    if (isLastElement) {
        viewBottomLine.visibility = View.GONE
    }

    if (isRedCircleVisible) {
        viewRedCircle.visibility = View.VISIBLE
    }
}
