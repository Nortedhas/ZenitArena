package com.ageone.zenit.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Flow.setNavigationBarColor
import com.ageone.zenit.Application.Coordinator.Flow.setStatusBarColor
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.Application.coordinator
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Icon
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Restaurant.RestaurantModel
import com.ageone.zenit.Modules.Restaurant.RestaurantView
import com.ageone.zenit.Modules.Restaurant.RestaurantViewModel
import com.ageone.zenit.Modules.Sales.SalesModel
import com.ageone.zenit.Modules.Sales.SalesView
import com.ageone.zenit.Modules.Sales.SalesViewModel
import com.ageone.zenit.R
import timber.log.Timber

fun FlowCoordinator.runFlowStock() {

    var flow: FlowStock? = FlowStock()

    flow?.let { flow ->
        setStatusBarColor(Color.parseColor("#09D0B8"))
        setNavigationBarColor(Color.RED)

        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)
        flow.colorStatusBar = Color.parseColor("#21D5BF")
        flow.colorNavigationBar = Color.RED

        Stack.flows.add(flow)
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

class FlowStock : BaseFlow() {

    private var models = FlowStockModels()

    override fun start() {
        onStarted()
        runModuleSales()
    }

    inner class FlowStockModels {
        var modelSales = SalesModel()
        var modelRestaurant = RestaurantModel()
    }

    fun runModuleSales() {
        val module = SalesView(
            InitModuleUI(
                firstIcon = Icon(
                    icon = R.drawable.ic_cross,
                    size = 30,
                    listener = {
                    }
                )

            )
        )

        module.viewModel.initialize(models.modelSales) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (SalesViewModel.EventType.valueOf(event)) {
                SalesViewModel.EventType.OnStockPressed -> {
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
                    icon = R. drawable.ic_cross,
                    size = 30,
                    listener = {
                    }
                )
            )
        )

        module.viewModel.initialize(models.modelRestaurant) {module.reload()}

        module.emitEvent = { event ->
            when(RestaurantViewModel.EventType.valueOf(event)) {

            }
        }
        settingsCurrentFlow.isBottomNavigationVisible = true

        push(module)
    }

}