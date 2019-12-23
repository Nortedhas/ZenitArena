package com.ageone.zenit.Modules.EventMap.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class EventMapItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewMap by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    val imageViewMap by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun EventMapItemViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewMap,
        imageViewMap
    )

    textViewMap
        .constrainTopToTopOf(constraintLayout,16)
        .constrainLeftToLeftOf(constraintLayout,16)

    imageViewMap
        .constrainTopToBottomOf(textViewMap,8)
        .fillHorizontally(16)
        .height(utils.tools.getActualSizeFromDes(185))


}

fun EventMapItemViewHolder.initialize(image: Int, text: String) {
    addImageFromGlide(imageViewMap,image,8)
    textViewMap.text = text
}
