package com.ageone.zenit.Modules.Profile

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.Modules.Profile.rows.ProfilePhotoViewHolder
import com.ageone.zenit.Modules.Profile.rows.ProfileTextViewHolder
import com.ageone.zenit.Modules.Profile.rows.initialize
import com.ageone.zenit.R
import com.ageone.zenit.SCAG.Image
import com.ageone.zenit.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class ProfileView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ProfileViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

//    var currentLoadImage: Image? = null

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Профиль"
        toolbar.textColor = Color.parseColor("#00ACEB")

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


        renderUIO()
        bindUI()

        onDeInit = {
            unBind()
        }

    }

    fun bindUI() {
//        compositeDisposable.addAll(
//            RxBus.listen(RxEvent.EventLoadImage::class.java).subscribe { event ->
//                currentLoadImage = event.loadedImage
//                bodyTable.adapter?.notifyDataSetChanged()
//            }
//        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val ProfilePhotoType = 0
        private val ProfileTextType = 1
        private val ProfileButtonType = 2

        override fun getItemCount() = 15//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ProfilePhotoType
            14 -> ProfileButtonType
            else -> ProfileTextType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ProfilePhotoType -> {
                    ProfilePhotoViewHolder(layout)
                }
                ProfileTextType -> {
                    ProfileTextViewHolder(layout)
                }
                ProfileButtonType -> {
                    ButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is ProfilePhotoViewHolder -> {
                    holder.initialize(R.drawable.pic_profile_1)
                }
                is ProfileTextViewHolder -> {
                    holder.initialize("ФИО", "Волкова Анна Александровна")
                }
                is ButtonViewHolder -> {
                    holder.initialize("Мой QR-код")
                }
            }
        }
    }
}

fun ProfileView.renderUIO() {

    renderBodyTable()
}


