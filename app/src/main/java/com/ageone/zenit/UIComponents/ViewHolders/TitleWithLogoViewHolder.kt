package com.ageone.zenit.UIComponents.ViewHolders

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.R
import yummypets.com.stevia.*

class TitleWithLogoViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val imageViewLogo by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    val textViewRegistration by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#00ACEB")
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView
    }

    init {

        renderUI()
    }

}

fun TitleWithLogoViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewLogo,
        textViewRegistration
    )


    imageViewLogo
        .width(utils.tools.getActualSizeFromDes(208))
        .height(utils.tools.getActualSizeFromDes(78))
        .constrainTopToTopOf(constraintLayout,51)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

    textViewRegistration
        .constrainTopToBottomOf(imageViewLogo,50)
        .constrainCenterXToCenterXOf(constraintLayout)
}

fun TitleWithLogoViewHolder.initialize(title: String) {
    textViewRegistration.text = title

    imageViewLogo.setBackgroundResource(R.drawable.zenit_logo)
}
