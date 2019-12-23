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
import com.ageone.zenit.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class ProfileView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ProfileViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val profileField = mutableMapOf<Int, String>()
    val profileValue = mutableMapOf<Int, String>()

    init {
//        viewModel.loadRealmData()

        profileField[1] = "ФИО"
        profileField[2] = "Пол"
        profileField[3] = "Дата рождения"
        profileField[4] = "Место рождения"
        profileField[5] = "Email"
        profileField[6] = "Номер телефона"
        profileField[7] = "Серия и номер паспорта"
        profileField[8] = "Кем и когда выдан"
        profileField[9] = "Снилс"
        profileField[10] = "Инн"
        profileField[11] = "Место работы/учёбы"
        profileField[12] = "Размер Куртки"

        profileValue[1] = "Волкова Анна Александровна"
        profileValue[2] = "Женский"
        profileValue[3] = "12.11.2000"
        profileValue[4] = "г. Санкт-Петербург"
        profileValue[5] = "volcovana@gmail.com"
        profileValue[6] = "+ 7 (951) 123 34 67"
        profileValue[7] = "1234 546789"
        profileValue[8] = "Отделом УФМС России по Ленинградской\nобл. Отделом УФМС России"
        profileValue[9] = "23456789123"
        profileValue[10] = "12357987789"
        profileValue[11] = "школа №42"
        profileValue[12] = "S"

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
        /*compositeDisposable.addAll(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val ProfilePhotoType = 0
        private val ProfileTextType = 1
        private val ProfileButtonType = 2

        override fun getItemCount() = 14//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ProfilePhotoType
            13 -> ProfileButtonType
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
                    holder.initialize(profileField[position] ?: "", profileValue[position] ?: "")
                }
                is ButtonViewHolder -> {
                    holder.initialize("Мой QR-код")
                    holder.button.setOnClickListener {
                        emitEvent?.invoke(ProfileViewModel.EventType.OnQrCodePressed.name)
                    }
                }
            }
        }
    }
}

fun ProfileView.renderUIO() {

    renderBodyTable()
}


