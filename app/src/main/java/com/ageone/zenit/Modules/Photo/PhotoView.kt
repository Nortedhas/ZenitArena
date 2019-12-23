package com.ageone.zenit.Modules.Photo

import android.graphics.Color
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import com.ageone.zenit.R
import yummypets.com.stevia.*
import kotlin.math.max

class PhotoView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = PhotoViewModel()

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.adjustViewBounds = true
        imageView.initialize()
        imageView
    }

    var scaleFactor = 1F

    var scaleDetector: ScaleGestureDetector? = null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleDetector?.onTouchEvent(event)
        return true
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scaleFactor *= detector?.scaleFactor ?: 0F

            scaleFactor = max(0.1F, scaleFactor.coerceAtMost(10F))

            imageViewPhoto.scaleX = scaleFactor
            imageViewPhoto.scaleY = scaleFactor

            return true
        }
    }

    init {
//        viewModel.loadRealmData()

        scaleDetector = ScaleGestureDetector(currentActivity?.applicationContext, ScaleListener())
        backgroundFullscreen.setBackgroundColor(Color.BLACK)

        toolbar.title = "Карты"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.BLACK)

        renderToolbar()

        renderUIO()
        bindUI()

        onDeInit = {
            unBind()
        }

        addImageFromGlide(imageViewPhoto,R.drawable.pic_photo,0)

    }

    fun bindUI() {
        /*compositeDisposable.addAll(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

}

fun PhotoView.renderUIO() {
    innerContent.subviews(
        imageViewPhoto
    )
    imageViewPhoto
        .constrainTopToTopOf(innerContent)
        .fillHorizontally()
        .height(utils.tools.getActualSizeFromDes(591))
}


