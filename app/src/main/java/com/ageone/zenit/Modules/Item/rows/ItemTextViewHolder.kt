package com.ageone.zenit.Modules.Item.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*

class ItemTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewItem by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView
    }

    val textViewDate by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#747480")
        textView
    }

    val textViewItemTitle by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.BLACK
        textView.maxLines = 1
        textView
    }

    val textViewItem by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView
    }

    val format = SimpleDateFormat("dd MMMM, yyyy", Locale("ru"))


    init {

        renderUI()
    }

}

fun ItemTextViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewItem,
        textViewDate,
        textViewItemTitle,
        textViewItem
    )

    imageViewItem
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()
        .height(utils.tools.getActualSizeFromDes(226))

    textViewDate
        .constrainTopToBottomOf(imageViewItem,17)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewItemTitle
        .constrainTopToBottomOf(textViewDate,2)
        .fillHorizontally(16)

    textViewItem
        .constrainTopToBottomOf(textViewItemTitle,2)
        .fillHorizontally(16)

}

fun ItemTextViewHolder.initialize(image: Int, date: Int, title: String, item: String) {
    addImageFromGlide(imageViewItem,image,0)

    textViewDate.text = format.format(Date(date.toLong() * 1000))
    textViewItemTitle.text = title
    textViewItem.text = item

}
