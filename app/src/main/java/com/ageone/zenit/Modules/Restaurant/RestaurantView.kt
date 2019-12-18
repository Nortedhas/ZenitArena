package com.ageone.zenit.Modules.Restaurant

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.zenit.Application.rxData
import com.ageone.zenit.R
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.Libraries.Alert.alertManager
import com.ageone.zenit.External.Libraries.Alert.double
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.Modules.Restaurant.rows.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import yummypets.com.stevia.*

class RestaurantView(initModuleUI: InitModuleUI = InitModuleUI()) :
    BaseModule(initModuleUI) {

    val viewModel = RestaurantViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        bindUI()
        runBlocking {
            viewModel.loadRealmData()
        }

        setBackgroundColor(Color.WHITE)


        toolbar.title = rxData.currentCompany?.name ?: ""
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.BLUE)
//        toolbar.toolbarImage = R.drawable.pic_food1
        renderToolbar()

        toolbar.countPush = rxData.selectedItems.size
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()

    }

    fun bindUI() {
        compositeDisposable.addAll(
            RxBus.listen(RxEvent.EventChangePushCount::class.java).subscribe { event ->
                toolbar.countPush = event.count
            },
            RxBus.listen(RxEvent.EventChangeCategory::class.java).subscribe { event ->
                viewModel.model.currentCategory = event.category
                viewModel.loadRealmData()
                bodyTable.adapter?.notifyDataSetChanged()
            },
            RxBus.listen(RxEvent.EventLoadCategories::class.java).subscribe { event ->
                Timber.i("Set categories")
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val RestaurantPreviewType = 0
        private val RestaurantTextType = 1
        private val RestaurantCardType = 3

        override fun getItemCount() = 2 + viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RestaurantPreviewType
            1 -> RestaurantTextType
            else -> RestaurantCardType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RestaurantPreviewType -> {
                    RestaurantPreviewViewHolder(layout)
                }
                RestaurantTextType -> {
                    RestaurantTextViewHolder(layout)
                }
                RestaurantCardType -> {
                    RestaurantCardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is RestaurantPreviewViewHolder -> {
                    holder.initialize(
                        rxData.currentCompany?.image?.original ?: "",
                        "${rxData.currentCompany?.name}",
                        "${rxData.currentCompany?.averageСheck}",
                        rxData.currentCompany?.txtWorkTimeInfo ?: "",
                        "${rxData.currentCompany?.deliveryFrom}",
                        "${rxData.currentCompany?.deliveryPrice}",
                        "${rxData.currentCompany?.rating}",
                        "${rxData.currentCompany?.commentsNum}")

                    holder.imageViewInfo.setOnClickListener {
                        rootModule.emitEvent?.invoke(RestaurantViewModel.EventType.OnInfoPressed.name)
                    }

                    holder.textViewReview.setOnClickListener {
                        rootModule.emitEvent?.invoke(RestaurantViewModel.EventType.OnReviewPressed.name)
                    }
                }

                is RestaurantTextViewHolder -> {
                    holder.initialize(rxData.currentCompany?.createCategoriesFromCompany() ?: listOf())
                }

                is RestaurantCardViewHolder -> {
                    val pos = position - 2
                    if (pos in viewModel.realmData.indices) {
                        val product = viewModel.realmData[pos].product
                        val price = viewModel.realmData[pos].priceWithSale
                        holder.initialize(
                            product.image?.original ?: "",
                            product.name,
                            price,
                            product.about
                        )

                        holder.buttonAdd.setOnClickListener {
                            if (rxData.selectedItems.isEmpty()) {
                                //add first item
                                rxData.productInBucketCompany = rxData.currentCompany
                                rxData.selectedItems += product

                            } else {

                                rxData.productInBucketCompany?.let { company ->
                                    if (company.hashId != rxData.currentCompany?.hashId) {
                                        //if items from other company

                                        alertManager.double(
                                            "Очистить корзину?",
                                            "В вашей корзине уже добавлены товары из другого заведения",
                                            button = "Нет",
                                            completion = {_,_ ->
                                                //dont clear
                                            },

                                            buttonCancel = "Очистить",
                                            completionCancel = {_,_ ->
                                                //clear and add item
                                                rxData.productInBucketCompany = rxData.currentCompany
                                                rxData.selectedItems = emptyList()
                                                rxData.selectedItems += product
                                            })
                                    } else {
                                        //if add item from the same company
                                        rxData.selectedItems += product
                                    }
                                }
                            }

                        }
                    }

                }
            }
        }
    }
}

fun RestaurantView.renderUIO() {

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false
}


