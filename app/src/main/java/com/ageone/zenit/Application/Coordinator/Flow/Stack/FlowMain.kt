package com.ageone.zenit.Application.Coordinator.Flow.Stack

import android.graphics.Color
import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Flow.setNavigationBarColor
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack.flows
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Extensions.Activity.clearLightStatusBar
import com.ageone.zenit.External.Icon
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Restaurant.RestaurantModel
import com.ageone.zenit.Modules.Restaurant.RestaurantView
import com.ageone.zenit.Modules.Restaurant.RestaurantViewModel
import com.ageone.zenit.R
import com.example.ageone.Modules.Restaurant.RestaurantListModel
import com.ageone.zenit.Modules.RestaurantList.RestaurantListView
import com.example.ageone.Modules.Restaurant.RestaurantListViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()


    flow?.let{ flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        setNavigationBarColor(Color.TRANSPARENT)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

        flow.colorNavigationBar = Color.TRANSPARENT
        flow.colorStatusBar = Color.parseColor("#21D5BF")
        currentActivity?.clearLightStatusBar(Color.parseColor("#21D5BF"),Color.WHITE)

        flows.add(flow)
    }

    flow?.onFinish = {
        flow?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is BaseModule) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

        flowStorage.deleteFlow(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }


//    flow?.start()
}

class FlowMain: BaseFlow() {

    private var models = FlowMainModels()

    override fun start() {
        onStarted()
        runModuleRestaurantList()
    }

    inner class FlowMainModels {
        var modelRestaurantList = RestaurantListModel()
        var modelRestaurant = RestaurantModel()
    }

    private fun runModuleRestaurantList() {
        val module = RestaurantListView(
            InitModuleUI(
                firstIcon = Icon(
                    icon = R.drawable.ic_cross,
                    size = 30,
                    listener = {
                    }
                ),
                isExpandToolbar = true
            )
        )

        module.viewModel.initialize(models.modelRestaurantList) { module.reload()}

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = {event ->

            when(RestaurantListViewModel.EventType.valueOf(event)) {
                RestaurantListViewModel.EventType.OnRestaurantPressed -> {
                    runModuleRestaurant()
                }
                RestaurantListViewModel.EventType.OnFilterPressed -> {
                }
                RestaurantListViewModel.EventType.OnBannerPressed -> {
                    runModuleRestaurant()
                }
            }
        }
        push(module)
    }

    fun runModuleRestaurant(){
        val module = RestaurantView(
            InitModuleUI(
                isBackPressed = true,
                firstIcon = Icon(
                    icon = R.drawable.ic_cross,
                    size = 30,
                    listener = {
                    }
                ),
                isExpandToolbar = true
            )
        )

        module.viewModel.initialize(models.modelRestaurant) {module.reload()}

        module.emitEvent = { event ->
            when(RestaurantViewModel.EventType.valueOf(event)) {
                RestaurantViewModel.EventType.OnReviewPressed -> {
                }
                RestaurantViewModel.EventType.OnInfoPressed -> {
                }
            }
        }
        settingsCurrentFlow.isBottomNavigationVisible = true

        push(module)
    }

}