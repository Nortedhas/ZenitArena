package com.ageone.zenit.Modules.EventReg

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.EventReg.rows.*
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
    
    val firstVariants = arrayOf(
        "Первая смена",
        "Вторая смена"
    )

    val secondVariants = arrayOf(
        "Инфо-волонтёры",
        "Волонтеры турникетной группы",
        "Волонтеры камер хранения",
        "Билетный контроль",
        "Волонтеры сопровождения\nVIP-гостей",
        "Волонтеры-экологи",
        "Волонтеры «Фан-променада» ",
        "Волонтеры контроля качества",
        "Волонтеры церемонии",
        "Волонтеры фото-позиций",
        "Инфо-волонтеры",
        "Волонтеры турникетной группы",
        "Волонтеры камер хранения",
        "Билетный контроль",
        "Волонтеры сопровождения\nVIP-гостей",
        "Волонтеры-экологи",
        "Волонтеры контроля качества",
        "Волонтеры церемонии",
        "Волонтеры фото-позиций"
    )
    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        textCategoryMap[0] = "Выберите смену"
        textCategoryMap[2] = "Выберите приоритетную функцию"
        textCategoryMap[4] = "Выберите вторую приоритетную функцию"

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
        private val EventRegSecondRadioButtonType = 2
        private val EventRegButtonType = 3
        private val EventRegLoadType = 4

        override fun getItemCount() = 8//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0,2,4 -> EventRegTextType
            1 -> EventRegRadioButtonType
            3,5 -> EventRegSecondRadioButtonType
            6 -> EventRegButtonType
            else -> EventRegLoadType
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
                EventRegSecondRadioButtonType -> {
                    EventRegSecondRadioButtonViewHolder(layout)
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
                    holder.initialize(firstVariants)
                }
                is EventRegSecondRadioButtonViewHolder -> {
                    holder.initialize(secondVariants)
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


