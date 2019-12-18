package com.ageone.zenit.Modules.Restaurant.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.R
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class RestaurantPreviewViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewPreview by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.GRAY)
        imageView.initialize()
        imageView
    }

    val imageViewGradient by lazy {
        val imageView = BaseImageView()
        val colors = intArrayOf(Color.TRANSPARENT,  Color.argb(0x88, 0,0,0))
        val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
        imageView.setImageDrawable(gradientDrawable)
        imageView.initialize()
        imageView
    }

    val imageViewWallet by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    val textViewCheck by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.WHITE
        textView
    }

    val imageViewClock by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    val textViewTimeDelivery by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.WHITE
        textView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 28F
        textView.textColor = Color.WHITE
        textView.typeface = Typeface.DEFAULT_BOLD
        textView
    }

    val imageViewInfo by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    val imageViewOrder by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    val textViewOrder by lazy {
        val textView = BaseTextView()
        textView.textSize = 12F
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val imageViewTruck by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    val textViewTruck by lazy {
        val textView = BaseTextView()
        textView.textSize = 12F
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val backRectangleStar by lazy {
        val view = BaseView()
        view.cornerRadius = 5.dp
        view.backgroundColor = Color.parseColor("#C4FCF5")
        view.initialize()
        view
    }

    val imageViewStar by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    val textViewRating by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#373737")
        textView.textSize = 12F
        textView
    }

    val backRectangleComment by lazy {
        val view = BaseView()
        view.cornerRadius = 5.dp
        view.backgroundColor = Color.parseColor("#C4FCF5")
        view.initialize()
        view
    }

    val imageViewComment by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    val textViewComment by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#373737")
        textView.textSize = 12F
        textView
    }

    val textViewReview by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#01A18D")
        textView.textSize = 12F
        textView.text = "Посмотреть отзывы"
        textView
    }

    init {
        renderUI()
    }

}

fun RestaurantPreviewViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewPreview,
        imageViewGradient,
        textViewName,
        imageViewClock,
        textViewTimeDelivery,
        imageViewWallet,
        textViewCheck,
        imageViewInfo,
        imageViewOrder,
        textViewOrder,
        imageViewTruck,
        textViewTruck,
        backRectangleStar,
        imageViewStar,
        textViewRating,
        backRectangleComment.subviews(
            imageViewComment,
            textViewComment
        ),
        textViewReview
    )

    imageViewPreview
        .constrainTopToTopOf(constraintLayout)
        .width(utils.variable.displayWidthDp)
        .height(utils.variable.displayWidthDp * .423F)

    imageViewGradient
        .constrainTopToTopOf(constraintLayout)
        .width(utils.variable.displayWidthDp)
        .height(utils.variable.displayWidthDp * .423F)

    textViewName
        .constrainBottomToBottomOf(imageViewPreview,12)
        .constrainLeftToLeftOf(imageViewPreview,16)

    imageViewClock
        .width(15)
        .height(15)
        .constrainCenterYToCenterYOf(textViewTimeDelivery)
        .constrainLeftToLeftOf(imageViewPreview,16)

    textViewTimeDelivery
        .constrainBottomToTopOf(textViewName,2)
        .constrainLeftToRightOf(imageViewClock,10)

    imageViewWallet
        .width(15)
        .height(15)
        .constrainCenterYToCenterYOf(textViewCheck)
        .constrainLeftToLeftOf(imageViewPreview,16)

    textViewCheck
        .constrainBottomToTopOf(textViewTimeDelivery,4)
        .constrainLeftToRightOf(imageViewWallet,10)

    imageViewInfo
        .width(27)
        .height(27)
        .constrainCenterYToCenterYOf(textViewName)
        .constrainRightToRightOf(imageViewPreview,20)

    imageViewOrder
        .width(14)
        .height(14)
        .constrainCenterYToCenterYOf(textViewOrder)
        .constrainCenterXToCenterXOf(imageViewClock)

    textViewOrder
        .constrainTopToBottomOf(imageViewPreview,12)
        .constrainLeftToRightOf(imageViewOrder, 10)

    imageViewTruck
        .width(18)
        .height(18)
        .constrainCenterYToCenterYOf(textViewTruck)
        .constrainCenterXToCenterXOf(imageViewClock)

    textViewTruck
        .constrainTopToBottomOf(imageViewOrder,8)
        .constrainLeftToRightOf(imageViewTruck, 10)

    backRectangleStar
        .width(64)
        .height(24)
        .constrainTopToBottomOf(textViewTruck,10)
        .constrainLeftToLeftOf(constraintLayout,16)

    imageViewStar
        .width(13)
        .height(13)
        .constrainLeftToLeftOf(backRectangleStar,12)
        .constrainTopToTopOf(backRectangleStar,5)

    textViewRating
        .constrainTopToTopOf(backRectangleStar,4)
        .constrainLeftToRightOf(imageViewStar,6)

    backRectangleComment
//        .width(64)
        .height(24)
        .constrainTopToBottomOf(textViewTruck,10)
        .constrainLeftToRightOf(backRectangleStar,14)

    imageViewComment
        .width(13)
        .height(13)
        .constrainLeftToLeftOf(backRectangleComment,12)
        .constrainTopToTopOf(backRectangleComment,6)

    textViewComment
        .constrainLeftToRightOf(imageViewComment,6)
        .constrainTopToTopOf(backRectangleComment,4)
        .constrainRightToRightOf(backRectangleComment,8)

    textViewReview
        .constrainBottomToBottomOf(backRectangleComment)
        .constrainRightToRightOf(constraintLayout,16)
}

fun RestaurantPreviewViewHolder.initialize(image: String, name:String, check: String, time: String, orderPrice: String, deliveryPrice: String, rating: String, commentCount: String) {

    addImageFromGlide(imageViewPreview, image,0)

    textViewCheck.text = "Средний чек: $check руб."
    textViewTimeDelivery.text = "Время доставки: $time"
    textViewName.text = name
    textViewOrder.text = "Заказ от $orderPrice руб."
    textViewTruck.text = "Доставка: $deliveryPrice руб."
    textViewRating.text = rating
    textViewComment.text = commentCount
}
