package com.ageone.zenit.Modules.Timing

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.UIComponents.ViewHolders.EventViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class TimingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = TimingViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val timingEvet = arrayOf(
        "Обучение волонтёров",
        "Обед (разные функции обедают в разное время)",
        "Обучение волонтёров",
        "Обучение волонтёров",
        "Обучение волонтёров",
        "Обучение волонтёров",
        "Обучение волонтёров",
        "Обучение волонтёров"
    )

    val time = arrayOf(
        "15:30",
        "15:30 - 16:00",
        "15:30",
        "15:30",
        "15:30 - 16:00",
        "15:30",
        "15:30 - 16:00",
        "15:30",
        "15:30"
    )

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Тиминг"
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

        private val TimingItemType = 0

        override fun getItemCount() = 7//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            else -> TimingItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                TimingItemType -> {
                    EventViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is EventViewHolder -> {
                    holder.initialize(time[position], timingEvet[position])
                }
            }
        }

    }

}

fun TimingView.renderUIO() {

    renderBodyTable()
}


