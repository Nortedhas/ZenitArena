package com.ageone.zenit.Modules.RestaurantList

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.ageone.zenit.Application.Coordinator.Flow.setStatusBarColor
import com.ageone.zenit.Application.rxData
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.Modules.RestaurantList.rows.RestaurantListImageViewHolder
import com.ageone.zenit.R
import com.example.ageone.Modules.Restaurant.RestaurantListViewModel
import com.example.ageone.Modules.Restaurant.rows.RestaurantListItemViewHolder
import com.example.ageone.Modules.Restaurant.rows.initialize
import yummypets.com.stevia.*

class RestaurantListView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = RestaurantListViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val imageViewFAB by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.width(25.dp)
        imageView.height(25.dp)
        imageView.setImageResource(R.drawable.ic_cross)
        imageView.initialize()
        imageView
    }

    init {
        viewModel.loadRealmData()

        setStatusBarColor(Color.TRANSPARENT)
//        setNavigationBarColor(Color.RED)
        backgroundFullscreen.setBackgroundColor(Color.GREEN)

        setBackgroundColor(Color.WHITE)

        toolbar.title = "Рестораны"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))

        /*toolbar.titleMarginTopInPercent = 50
        toolbar.titleMarginLeftInPercent = 50
        toolbar.toolbarImage = R.drawable.food2
        toolbar.heightInPercent = 30*/

        renderToolbar()

        imageViewFAB.setOnClickListener {
            //emitEvent?.invoke(RestaurantListViewModel.EventType.OnFilterPressed.name)
        }

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()

        onDeInit = {
//            bottomSheetManager.removeBottomSheetFromModule(imageViewFAB)
            unBind()
        }
    }

    fun bindUI() {
        compositeDisposable.addAll(
            RxBus.listen(RxEvent.EventChangePushCount::class.java).subscribe { event ->
                toolbar.countPush = event.count
            },
            RxBus.listen(RxEvent.EventChangeFilter::class.java).subscribe { event ->
                viewModel.loadRealmData()
                bodyTable.adapter?.notifyDataSetChanged()
            }

        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val RestaurantListImageType = 0
        private val RestaurantListItemType = 1

        override fun getItemCount() = 1 + viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RestaurantListImageType
            else -> RestaurantListItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RestaurantListImageType -> {
                    RestaurantListImageViewHolder(layout)
                }
                RestaurantListItemType -> {
                    RestaurantListItemViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is RestaurantListImageViewHolder -> {
//                    holder.initialize()

                    holder.onTap = {
                        rootModule.emitEvent?.invoke(RestaurantListViewModel.EventType.OnBannerPressed.name)
                    }
                }
                is RestaurantListItemViewHolder -> {
                    val pos = position - 1
                    if (pos in viewModel.realmData.indices) {
                        val company = viewModel.realmData[pos]

                        holder.initialize(
                            company.image?.original ?: "",
                            company.name,
                            company.tags.joinToString(),
                            "Заказ от ${company.deliveryFrom} руб.",
                            R.drawable.ic_cross,
                            company.rating
                        )
                        holder.constraintLayout.setOnClickListener {
                            rxData.currentCompany = company
                            rootModule.emitEvent?.invoke(RestaurantListViewModel.EventType.OnRestaurantPressed.name)
                        }
                    }

                }
            }
        }
    }
}

fun RestaurantListView.renderUIO() {

    innerContent.subviews(
        bodyTable,
        imageViewFAB
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false

    imageViewFAB
        .constrainRightToRightOf(innerContent,5)
        //.constrainBottomToBottomOf(innerContent,30)
        .constrainTopToTopOf(innerContent,30)

    /*bottomSheetManager
        .applyBottomSheetToModule(imageViewFAB, renderBottomSheetContent())*/
}

fun RestaurantListView.renderBottomSheetContent(): BaseRecyclerView {
    return BaseRecyclerView().apply {
        layoutManager = LinearLayoutManager(this.context)
        adapter = viewAdapter
    }
}

