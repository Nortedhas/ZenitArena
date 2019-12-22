package com.ageone.zenit.Modules.Status

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Status.rows.StatusImageViewHolder
import com.ageone.zenit.Modules.Status.rows.StatusTitleBeforePresentViewHolder
import com.ageone.zenit.Modules.Status.rows.StatusVisitedViewHolder
import com.ageone.zenit.Modules.Status.rows.initialize
import com.ageone.zenit.R
import yummypets.com.stevia.*

class StatusView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = StatusViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = ""

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

        private val StatusTitleBeforePresentType = 0
        private val StatusVisitedType = 1
        private val StatusImageType = 2

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> StatusTitleBeforePresentType
            1 -> StatusVisitedType
            2 -> StatusImageType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                StatusTitleBeforePresentType -> {
                    StatusTitleBeforePresentViewHolder(layout)
                }

                StatusVisitedType -> {
                    StatusVisitedViewHolder(layout)
                }

                StatusImageType -> {
                    StatusImageViewHolder(layout)
                }

                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            for (prize in Prizes.values()) {
                if (viewModel.model.countMatches < prize.matchNum) {
                    viewModel.model.countEventsBeforePrize = prize.matchNum - viewModel.model.countMatches
                    viewModel.model.image = when (prize.ordinal) {
                        1 -> R.drawable.pic_status_1
                        2 -> R.drawable.pic_status_2
                        3 -> R.drawable.pic_status_3
                        4 -> R.drawable.pic_status_4
                        5 -> R.drawable.pic_status_5
                        6 -> R.drawable.pic_status_6
                        else -> R.drawable.pic_status_0
                    }
                    break
                }
            }

            when (holder) {
                is StatusTitleBeforePresentViewHolder -> {
                    holder.initialize(viewModel.model.countEventsBeforePrize)
                }

                is StatusVisitedViewHolder -> {
                    holder.initialize(viewModel.model.countMatches)
                }

                is StatusImageViewHolder -> {
                    holder.initialize(viewModel.model.image, viewModel.model.countMatches)
                }

            }

        }

    }

}

fun StatusView.renderUIO() {

    renderBodyTable()
}


