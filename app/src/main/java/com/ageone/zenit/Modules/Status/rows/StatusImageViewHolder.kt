package com.ageone.zenit.Modules.Status.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
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
        textView
    }

    val textViewBalance2 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewBalance3 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewBalance4 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewBalance5 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewBalance6 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewBalance7 by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 10F
        textView.textColor = Color.parseColor("#3ACCFF")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

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
        .constrainBottomToBottomOf(constraintLayout,32)
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
        .constrainRightToRightOf(imageView, utils.tools.getActualSizeFromDes(83).toInt())

    textViewBalance4
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(312).toInt())
        .constrainLeftToLeftOf(imageView, utils.tools.getActualSizeFromDes(16).toInt())

    textViewBalance5
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(575).toInt())
        .constrainRightToRightOf(imageView, utils.tools.getActualSizeFromDes(41).toInt())

    textViewBalance6
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(732).toInt())
        .constrainLeftToLeftOf(imageView, utils.tools.getActualSizeFromDes(95).toInt())

    textViewBalance7
        .constrainTopToTopOf(imageView, utils.tools.getActualSizeFromDes(888).toInt())
        .constrainRightToRightOf(imageView, utils.tools.getActualSizeFromDes(112).toInt())
}

fun StatusImageViewHolder.initialize(image: Int) {
    addImageFromGlide(imageView, image, 0)
    textViewBalance1.text = "Вам осталось 2"
    textViewBalance2.text = "Вам осталось 2"
    textViewBalance3.text = "Вам осталось 2"
    textViewBalance4.text = "Вам осталось 2"
    textViewBalance5.text = "Вам осталось 2"
    textViewBalance6.text = "Вам осталось 2"
    textViewBalance7.text = "Вам осталось 2"

    /*imageView
        .width(utils.tools.getActualSizeFromDes(360))
        .height(utils.tools.getActualSizeFromDes(885))*/
}
