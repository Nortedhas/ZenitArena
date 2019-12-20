package com.ageone.zenit.Modules.Quiz.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class QuizTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    val imageViewQuiz by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    val textViewQuizTitle by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.BLACK
        textView.textSize = 24F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.initialize()
        textView
    }

    val textViewQuizQuestion by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.BLACK
        textView.textSize = 17F
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun QuizTextViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewQuiz,
        textViewQuizTitle,
        textViewQuizQuestion
    )

    imageViewQuiz
        .constrainTopToTopOf(constraintLayout)
        .fillHorizontally()
        .height(utils.tools.getActualSizeFromDes(226))

    textViewQuizTitle
        .constrainTopToBottomOf(imageViewQuiz,15)
        .fillHorizontally(16)

    textViewQuizQuestion
        .constrainTopToBottomOf(textViewQuizTitle,10)
        .fillHorizontally(16)
}

fun QuizTextViewHolder.initialize(image: Int,title: String, question: String) {
    addImageFromGlide(imageViewQuiz,image,0)

    textViewQuizTitle.text = title
    textViewQuizQuestion.text = question
}
