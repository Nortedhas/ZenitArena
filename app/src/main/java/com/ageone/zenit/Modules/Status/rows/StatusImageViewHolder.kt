package com.ageone.zenit.Modules.Status.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import com.ageone.zenit.Modules.Status.Prizes
import yummypets.com.stevia.*

class StatusImageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }
    
    val textViewBalance1 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView
    }

    val textViewBalance2 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView
    }

    val textViewBalance3 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView
    }

    val textViewBalance4 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView
    }

    val textViewBalance5 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView
    }

    val textViewBalance6 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView
    }

    val textViewBalance7 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView
    }

    val textViews = arrayListOf(
        textViewBalance1,
        textViewBalance2,
        textViewBalance3,
        textViewBalance4,
        textViewBalance5,
        textViewBalance6,
        textViewBalance7
    )

    init {

        renderUI()
    }

}

fun StatusImageViewHolder.renderUI() {
    constraintLayout.subviews(
        imageView,
        textViewBalance1,
        textViewBalance2,
        textViewBalance3,
        textViewBalance4,
        textViewBalance5,
        textViewBalance6,
        textViewBalance7
    )

    val width = utils.tools.getActualSizeFromDes(364)
    val height = utils.tools.getActualSizeFromDes(885)

    imageView
        .constrainTopToTopOf(constraintLayout, 32)
        .constrainRightToRightOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainBottomToBottomOf(constraintLayout,48)
        .width(width)
        .height(height)

    textViewBalance1
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainRightToRightOf(constraintLayout, utils.tools.getActualSizeFromDes(28).toInt())

    textViewBalance2
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(8).toInt())
        .constrainLeftToLeftOf(imageView, utils.tools.getActualSizeFromDes(40).toInt())

    textViewBalance3
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(405).toInt())
        .constrainRightToRightOf(imageView, utils.tools.getActualSizeFromDes(85).toInt())

    textViewBalance4
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(312).toInt())
        .constrainLeftToLeftOf(imageView, utils.tools.getActualSizeFromDes(16).toInt())

    textViewBalance5
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(575).toInt())
        .constrainRightToRightOf(imageView, utils.tools.getActualSizeFromDes(37).toInt())

    textViewBalance6
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(732).toInt())
        .constrainLeftToLeftOf(imageView, utils.tools.getActualSizeFromDes(95).toInt())

    textViewBalance7
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(888).toInt())
        .constrainRightToRightOf(imageView, utils.tools.getActualSizeFromDes(107).toInt())
}

fun StatusImageViewHolder.initialize(image: Int, countVisitedMatches: Int) {
    addImageFromGlide(imageView, image, 0)

    for (prize in Prizes.values()) {
        if (countVisitedMatches < prize.matchNum) {
            textViews[prize.ordinal].apply {
                text = "Вам осталось ${prize.matchNum - countVisitedMatches}"
                visibility = View.VISIBLE
            }

        }
    }
}
