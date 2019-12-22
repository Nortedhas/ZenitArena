package com.ageone.zenit.Modules.EventItem.rows

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Button.BaseButton
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class EventItemButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val viewEventItem by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#00ACEB")
        view.cornerRadius = 8.dp
        view.initialize()
        view
    }

    val textViewEventItem by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.textColor = Color.WHITE
        textView.initialize()
        textView
    }

    val imageViewEventItem by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun EventItemButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        viewEventItem.subviews(
            textViewEventItem,
            imageViewEventItem
        )
    )

    viewEventItem
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(16)
        .height(50)

    textViewEventItem
        .constrainCenterYToCenterYOf(viewEventItem)
        .constrainCenterXToCenterXOf(viewEventItem)

    imageViewEventItem
        .constrainLeftToLeftOf(viewEventItem,50)
        .constrainCenterYToCenterYOf(viewEventItem)
        .height(27)
        .width(27)
}

fun EventItemButtonViewHolder.initialize(image: Int, text: String) {
    addImageFromGlide(imageViewEventItem,image,0)
    textViewEventItem.text = text
}
