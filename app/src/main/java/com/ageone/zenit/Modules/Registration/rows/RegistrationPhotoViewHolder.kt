package com.ageone.zenit.Modules.Registration.rows

import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
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

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = 80.dp
        imageView.setBackgroundColor(Color.GRAY)
        imageView.backgroundColor = Color.GRAY
        imageView.initialize()
        imageView
    }

    val imageViewIconPhoto by lazy {
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
        imageViewPhoto,
        imageViewIconPhoto
    )


    textViewLoad
        .constrainTopToTopOf(constraintLayout,22)
        .constrainCenterXToCenterXOf(constraintLayout)

    imageViewPhoto
        .constrainTopToBottomOf(textViewLoad,17)
        .constrainCenterXToCenterXOf(constraintLayout)
        .height(80)
        .width(80)

    imageViewIconPhoto
        .constrainCenterXToCenterXOf(imageViewPhoto)
        .constrainCenterYToCenterYOf(imageViewPhoto)
        .height(24)
        .width(24)
}

fun RegistrationPhotoViewHolder.initialize(imagePreview: String?) {

    textViewLoad.text = "Загрузить фото"

    addImageFromGlide(imageViewIconPhoto,R.drawable.ic_photo)
    if(!imagePreview.isNullOrBlank()) {
        addImageFromGlide(imageViewPhoto,imagePreview,40.dp)
        imageViewIconPhoto.visibility = View.GONE
    } else {
        imageViewIconPhoto.visibility = View.VISIBLE
    }
}
