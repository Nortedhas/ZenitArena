package com.ageone.zenit.Modules.Item

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Item.rows.ItemTextViewHolder
import com.ageone.zenit.Modules.Item.rows.initialize
import com.ageone.zenit.R
import yummypets.com.stevia.*

class ItemView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ItemViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Матч Кубка Росси" //TODO : change
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

        private val ItemTextType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ItemTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ItemTextType -> {
                    ItemTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is ItemTextViewHolder -> {
                    holder.initialize(
                        R.drawable.pic_item_1,
                        1569196800,
                        "Матч Кубка России",
                        "Уже завтра на «Газпром Арене» состоится матч Кубка России между «Зенитом» и «Томью». \n\n" +
                                "Самое время вспомнить статистику встреч этих команд." +
                                " Футбольный клубы встречались на поле 19 раз, из которых ФК «Зенит» одержал победу 10 раз, а ПФК «Томь» всего 3." +
                                " В ничью закончились 6 встреч. Последнюю из трех своих побед томский клуб одержал 10 лет назад со счетом 2:0. " +
                                "В свою очередь сине-бело-голубые не сдают позиции и всего два раза отпускали томичей с ничейным счетом за прошедшие 10 лет.\n\n" +
                                "Верим в любимый футбольный клуб и ждем победу в завтрашней встрече! ")
                }

            }

        }

    }

}

fun ItemView.renderUIO() {

    renderBodyTable()
}


