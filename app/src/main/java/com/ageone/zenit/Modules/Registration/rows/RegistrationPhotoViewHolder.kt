package com.ageone.zenit.Modules.Registration.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import com.ageone.zenit.R
import yummypets.com.stevia.*

class RegistrationPhotoViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewLoad by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#00ACEB")
        textView.textSize = 14F
        textView
    }

    val viewPhoto by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#D7D7D8")
        view.cornerRadius = 80.dp
        view.initialize()
        view
    }

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun RegistrationPhotoViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewLoad,
        viewPhoto.subviews(
            imageViewPhoto
        )
    )

    textViewLoad
        .constrainTopToTopOf(constraintLayout,22)
        .constrainCenterXToCenterXOf(constraintLayout)

    viewPhoto
        .constrainTopToBottomOf(textViewLoad,17)
        .constrainCenterXToCenterXOf(constraintLayout)
        .height(80)
        .width(80)

    imageViewPhoto
        .constrainCenterXToCenterXOf(viewPhoto)
        .constrainCenterYToCenterYOf(viewPhoto)
        .height(24)
        .width(24)
}

fun RegistrationPhotoViewHolder.initialize() {

    textViewLoad.text = "Загрузить фото"

    addImageFromGlide(imageViewPhoto,R.drawable.ic_photo)
}
