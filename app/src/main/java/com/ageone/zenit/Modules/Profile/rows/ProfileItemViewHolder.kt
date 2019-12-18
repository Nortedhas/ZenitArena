package com.ageone.zenit.Modules.Profile.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import com.ageone.zenit.R
import yummypets.com.stevia.*

class ProfileItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewProfile by lazy {
        val imageView = BaseImageView()
        imageView.initialize()
        imageView
    }

    val textViewProfile by lazy {
        val textView = BaseTextView()
        textView.textSize = 17F
        textView.textColor = Color.parseColor("#242839")
        textView
    }

    val textViewProfileDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#848484")
        textView.width(utils.variable.displayWidthDp - 100)
        textView
    }

    val imageViewNext by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    init {
        renderUI()
    }

}

fun ProfileItemViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewProfile,
        imageViewProfile,
        textViewProfileDescription,
        imageViewNext
    )

    textViewProfile
        .constrainTopToTopOf(constraintLayout,24)
        .constrainLeftToRightOf(imageViewProfile,16)

    imageViewProfile
        .constrainLeftToLeftOf(constraintLayout,16)
        .constrainTopToTopOf(textViewProfile,3)
        .width(25)
        .height(25)

    textViewProfileDescription
        .constrainTopToBottomOf(textViewProfile,5)
        .constrainLeftToRightOf(imageViewProfile,16)
        .width(utils.tools.getActualSizeFromDes(276))

    imageViewNext
        .width(10)
        .height(15)
        .constrainTopToBottomOf(textViewProfile,4)
        .constrainRightToRightOf(constraintLayout,30)
}

fun ProfileItemViewHolder.initialize(image: Int, text: String, description: String) {
    addImageFromGlide(imageViewProfile,image,1)

    textViewProfile.text = text
    textViewProfileDescription.text = description
}
