package com.ageone.zenit.Modules.News.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*

class NewsItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewNews by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    val textViewDate by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#747480")
        textView.initialize()
        textView
    }

    val textViewNewsTitle by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.BLACK
        textView.maxLines = 1
        textView.initialize()
        textView
    }

    val textViewNews by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.BLACK
        textView.maxLines = 2
        textView.initialize()
        textView
    }

    val textViewContinue by lazy {
        val textView = BaseTextView()
        textView.textSize = 12F
        textView.textColor = Color.parseColor("#787880")
        textView.initialize()
        textView
    }

    val viewBackContinue by lazy {
        val view = BaseView()
        view.backgroundColor = Color.TRANSPARENT
        view
    }

    val format = SimpleDateFormat("dd MMMM, yyyy", Locale("ru"))

    init {

        renderUI()
    }

}

fun NewsItemViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewNews,
        textViewDate,
        textViewNewsTitle,
        textViewNews,
        viewBackContinue,
        textViewContinue
    )

    imageViewNews
        .constrainTopToTopOf(constraintLayout,15)
        .fillHorizontally(16)
        .height(utils.tools.getActualSizeFromDes(185))

    textViewDate
        .constrainTopToBottomOf(imageViewNews,10)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewNewsTitle
        .constrainTopToBottomOf(textViewDate,3)
        .fillHorizontally(16)

    textViewNews
        .constrainTopToBottomOf(textViewNewsTitle,2)
        .fillHorizontally(16)

    viewBackContinue
        .constrainCenterYToCenterYOf(textViewContinue)
        .constrainCenterXToCenterXOf(textViewContinue)
        .height(30)
        .width(100)

    textViewContinue
        .constrainTopToBottomOf(textViewNews,2)
        .constrainRightToRightOf(constraintLayout,16)
        .constrainBottomToBottomOf(constraintLayout,2)
}

fun NewsItemViewHolder.initialize(image: Int, date: Int,title: String, news: String) {

    addImageFromGlide(imageViewNews,image,8)

    textViewDate.text = format.format(Date(date.toLong() * 1000))
    textViewNewsTitle.text = title
    textViewNews.text = news

    textViewContinue.text = "Продолжить >"
}
