package com.ageone.zenit.Modules.Photo

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import com.ageone.zenit.R
import yummypets.com.stevia.*

class PhotoView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = PhotoViewModel()

    val imageViewPhoto by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    init {
//        viewModel.loadRealmData()

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


