package com.ageone.zenit.UIComponents.ViewHolders

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantImageItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewFood by lazy {
        val image = BaseImageView()
        image.setBackgroundColor(Color.GRAY)
        image.initialize()
        image
    }

    init {

        renderUI()
    }

}

fun RestaurantImageItemViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewFood
    )

    imageViewFood
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()
}

fun RestaurantImageItemViewHolder.initialize(image: String) {

    constraintLayout
        .width(utils.variable.displayWidthDp)

    imageViewFood
        .width(utils.variable.displayWidthDp)
        .height(utils.variable.displayWidthDp * .402F)

    addImageFromGlide(imageViewFood, image,1)
}
