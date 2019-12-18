package com.ageone.zenit.Modules.Sales

import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.zenit.Application.Coordinator.Router.bottomSheetManager
import com.ageone.zenit.Application.rxData
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.Modules.Sales.rows.SalesCardViewHolder
import com.ageone.zenit.Modules.Sales.rows.initialize
import timber.log.Timber
import yummypets.com.stevia.*

class SalesView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = SalesViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        viewModel.loadRealmData()

        setBackgroundColor(Color.WHITE)


        toolbar.title = "Акции"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        renderToolbar()
        toolbar.countPush = rxData.selectedItems.size
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        compositeDisposable.addAll(
            RxBus.listen(RxEvent.EventChangePushCount::class.java).subscribe { pushCount ->
                toolbar.countPush = pushCount.count
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val SalesCardType = 0

        override fun getItemCount() = 3 //viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            else -> SalesCardType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                SalesCardType -> {
                    SalesCardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            when (holder) {
                is SalesCardViewHolder -> {
                    if (position in viewModel.realmData.indices) {
                        val sale = viewModel.realmData[position]
                        holder.initialize(
                            sale.image?.original ?: "",
                            sale.name,
                            sale.txtInfo
                        )

                        holder.constraintLayout.setOnClickListener {
                            rxData.currentCompany = utils.realm.user.getObjectById(sale.companyHashId)//change: companyHashId
                            Timber.i("Company: ${rxData.currentCompany}")
                            rootModule.emitEvent?.invoke(SalesViewModel.EventType.OnStockPressed.name)
                        }
                    }
                }
            }

        }
    }
}

fun SalesView.renderUIO() {

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally(0)
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false

//    bottomSheetManager.applyBottomSheetToModule(toolbar.firstIcon, renderBottomSheetContent())
}

fun SalesView.renderBottomSheetContent(): BaseRecyclerView {
    return com.ageone.zenit.External.Base.RecyclerView.BaseRecyclerView().apply {
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
        adapter = viewAdapter
    }
}


