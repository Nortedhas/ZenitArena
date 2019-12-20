package com.ageone.zenit.Modules.Event.rows

import android.graphics.Color
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.ConstraintLayout.setOnlyTopRoundedCorners
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews
import yummypets.com.stevia.textColor

class EventItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewMonth by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    val view by lazy {
        val view = BaseView()
        view.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view?.width!!, (view?.height!! + 8F).toInt(), 8F)
            }
        }
        view.clipToOutline = true
        view.initialize()
    // 	imageView.elevation = 5F.dp
        view
    }

    init {

        renderUI()
    }

}

fun EventItemViewHolder.renderUI() {
    constraintLayout.subviews(

    )


}

fun EventItemViewHolder.initialize() {

}
