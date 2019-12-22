package com.ageone.zenit.Modules.EventReg

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.EventReg.rows.EventRegLoadViewHolder
import com.ageone.zenit.Modules.EventReg.rows.EventRegRadioButtonViewHolder
import com.ageone.zenit.Modules.EventReg.rows.EventRegTextViewHolder
import com.ageone.zenit.Modules.EventReg.rows.initialize
import com.ageone.zenit.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class EventRegView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EventRegViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val textCategoryMap = mutableMapOf<Int, String>()

    val variantsMap = mutableMapOf<Int, String>()
    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        textCategoryMap[0] = "Выберите смену"
        textCategoryMap[3] = "Выберите приоритетную функцию"
        textCategoryMap[14] = "Выберите вторую приоритетную функцию"

        variantsMap[1] = "Первая смена"
        variantsMap[2] = "Вторая смена"
        variantsMap[4] = "Инфо-волонтёры"
        variantsMap[5] = "Волонтеры турникетной группы"
        variantsMap[6] = "Волонтеры камер хранения"
        variantsMap[7] = "Билетный контроль"
        variantsMap[8] = "Волонтеры сопровождения VIP-гостей"
        variantsMap[9] = "Волонтеры-экологи"
        variantsMap[10] = "Волонтеры «Фан-променада» "
        variantsMap[11] = "Волонтеры контроля качества"
        variantsMap[12] = "Волонтеры церемонии"
        variantsMap[13] = "Волонтеры фото-позиций"
        variantsMap[15] = "Инфо-волонтеры"
        variantsMap[16] = "Волонтеры турникетной группы"
        variantsMap[17] = "Волонтеры камер хранения"
        variantsMap[18] = "Билетный контроль"
        variantsMap[19] = "Волонтеры сопровождения VIP-гостей"
        variantsMap[20] = "Волонтеры-экологи"
        variantsMap[21] = "Волонтеры контроля качества"
        variantsMap[22] = "Волонтеры церемонии"
        variantsMap[23] = "Волонтеры фото-позиций"

        toolbar.title = "Регистрация на матч"
        toolbar.textColor = Color.parseColor("#00ACEB")

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        bodyTable.setItemViewCacheSize(25)

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

        private val EventRegTextType = 0
        private val EventRegRadioButtonType = 1
        private val EventRegButtonType = 2
        private val EventRegLoadType = 3

        override fun getItemCount() = 26//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0,3,14 -> EventRegTextType
            24 -> EventRegButtonType
            25 -> EventRegLoadType
            else -> EventRegRadioButtonType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                EventRegTextType -> {
                    EventRegTextViewHolder(layout)
                }
                EventRegRadioButtonType -> {
                    EventRegRadioButtonViewHolder(layout)
                }
                EventRegButtonType -> {
                    ButtonViewHolder(layout)
                }
                EventRegLoadType -> {
                    EventRegLoadViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is EventRegTextViewHolder -> {
                    holder.initialize(textCategoryMap[position] ?: "")
                }
                is EventRegRadioButtonViewHolder -> {
                    holder.initialize(variantsMap[position] ?: "")
                }
                is ButtonViewHolder -> {
                    holder.initialize("Отправить")
                }

                is EventRegLoadViewHolder -> {
                    holder.initialize()
                }
            }
        }
    }
}

fun EventRegView.renderUIO() {

    renderBodyTable()
}


