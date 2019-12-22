package com.ageone.zenit.Modules.QR

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import com.ageone.zenit.R
import yummypets.com.stevia.*

class QRView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = QRViewModel()

    val imageViewIconPhoto by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    val textViewQR by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#B3B3B5")
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.initialize()
        textView
    }

    val imageViewQR by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }
    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Мой QR код"
        toolbar.textColor = Color.parseColor("#00ACEB")

        renderToolbar()

        imageViewIconPhoto.setBackgroundResource(R.drawable.ic_photo_fill)
        textViewQR.text = "Пожалуйста, поднесите ваш смартфон\nк сканирующему устройству"
        addImageFromGlide(imageViewQR,R.drawable.pic_qr_1,0)

        renderUIO()
        bindUI()

        onDeInit = {
            unBind()
        }

    }

    fun bindUI() {
        /*compositeDisposable.addAll(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

}

fun QRView.renderUIO() {
    innerContent.subviews(
        imageViewQR,
        textViewQR,
        imageViewIconPhoto
    )

    imageViewQR
        .constrainCenterXToCenterXOf(innerContent)
        .constrainCenterYToCenterYOf(innerContent)
        .height(utils.tools.getActualSizeFromDes(230))
        .width(utils.tools.getActualSizeFromDes(230))

    textViewQR
        .constrainBottomToTopOf(imageViewQR, 16)
        .constrainCenterXToCenterXOf(innerContent)

    imageViewIconPhoto
        .constrainBottomToTopOf(textViewQR,24)
        .constrainCenterXToCenterXOf(innerContent)
        .width(30)
        .height(30)
}


