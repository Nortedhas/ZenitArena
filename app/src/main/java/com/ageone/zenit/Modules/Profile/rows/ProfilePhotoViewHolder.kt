package com.ageone.zenit.Modules.Profile.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class ProfilePhotoViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = 80.dp
        imageView.setBackgroundColor(Color.GRAY)
        imageView.backgroundColor = Color.GRAY
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun ProfilePhotoViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewPhoto
    )

    imageViewPhoto
        .constrainTopToTopOf(constraintLayout,32)
        .constrainCenterXToCenterXOf(constraintLayout)
        .height(120)
        .width(120)


}

fun ProfilePhotoViewHolder.initialize(imagePreview: Int) {
    addImageFromGlide(imageViewPhoto,imagePreview ,60.dp)
}
