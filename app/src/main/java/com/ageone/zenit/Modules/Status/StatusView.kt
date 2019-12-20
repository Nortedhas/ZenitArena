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

            when (holder) {
                is StatusTitleBeforePresentViewHolder -> {
                    holder.initialize(2)
                }

                is StatusVisitedViewHolder -> {
                    holder.initialize(2)
                }

                is StatusImageViewHolder -> {
                    holder.initialize(R.drawable.pic_status_2)
                }

            }

        }

    }

}

fun StatusView.renderUIO() {

    renderBodyTable()
}


