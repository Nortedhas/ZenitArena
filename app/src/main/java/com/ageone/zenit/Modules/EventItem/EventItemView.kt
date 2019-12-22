package com.ageone.zenit.Modules.EventItem

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.EventItem.rows.EventImageViewHolder
import com.ageone.zenit.Modules.EventItem.rows.EventItemButtonViewHolder
import com.ageone.zenit.Modules.EventItem.rows.EventItemTextViewHolder
import com.ageone.zenit.Modules.EventItem.rows.initialize
import com.ageone.zenit.R
import com.ageone.zenit.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class EventItemView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EventItemViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val isRegistered = false

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Лига Чемпионов УЕФА"
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

        private val EventImageType = 0
        private val EventTextType = 1
        private val EventButtonType = 2

        override fun getItemCount() = if(isRegistered) 4 else 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> EventImageType
            1 -> EventTextType
            else -> EventButtonType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                EventImageType -> {
                    EventImageViewHolder(layout)
                }
                EventTextType -> {
                    EventItemTextViewHolder(layout)
                }
                EventButtonType -> {
                    if(isRegistered)
                        EventItemButtonViewHolder(layout)
                    else
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
                is EventImageViewHolder -> {
                    holder.initialize(R.drawable.pic_item_1)
                }
                is EventItemTextViewHolder -> {
                    holder.initialize(
                        1571511600,
                        "Лига Чемпионов УЕФА Зенит-Лион",
                        "Уже завтра на «Газпром Арене» состоится матч Кубка России между «Зенитом» и «Томью». " +
                            "Самое время вспомнить статистику встреч этих команд. Футбольный клубы встречались на поле 19 раз, из которых ФК «Зенит» одержал победу 10 раз, а ПФК «Томь» всего 3. " +
                            "В ничью закончились 6 встреч. Последнюю из трех своих побед томский клуб одержал 10 лет назад со счетом 2:0 " +
                            "В свою очередь сине-бело-голубые не сдают позиции и всего два раза отпускали томичей с ничейным счетом за прошедшие 10 лет.")
                }
                is EventItemButtonViewHolder -> {
                    when(position) {
                        2 -> {
                            holder.initialize(R.drawable.ic_timing,"Тайминг")
                            holder.viewEventItem.setOnClickListener {
                                emitEvent?.invoke(EventItemViewModel.EventType.OnTimingPressed.name)
                            }
                        }
                        3 -> {
                            holder.initialize(R.drawable.ic_map,"Как добраться")
                            holder.viewEventItem.setOnClickListener {
                                emitEvent?.invoke(EventItemViewModel.EventType.OnMapPressed.name)
                            }
                        }
                    }
                }

                is ButtonViewHolder -> {
                    holder.initialize("Зарегистрироваться")
                    holder.button.setOnClickListener {
                        emitEvent?.invoke(EventItemViewModel.EventType.OnRegistrationPressed.name)
                    }
                }
            }
        }
    }
}

fun EventItemView.renderUIO() {

    renderBodyTable()
}


