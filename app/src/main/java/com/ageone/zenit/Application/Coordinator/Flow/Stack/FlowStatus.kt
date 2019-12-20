package com.ageone.zenit.Application.Coordinator.Flow.Stack

import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.ModuleInterface
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Status.StatusView
import com.ageone.zenit.Modules.Status.StatusModel
import com.ageone.zenit.Modules.Status.StatusViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowStatus() {

    var flow: FlowStatus? = FlowStatus()

    flow?.let { flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        flow?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is ModuleInterface) {
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

class FlowStatus(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowStatModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleStatus()
    }

    inner class FlowStatModels {
        val modelStatus = StatusModel()
    }

    fun runModuleStatus() {
        val module = StatusView(InitModuleUI(
            isToolbarHidden = true
        ))

        module.viewModel.initialize(models.modelStatus) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (StatusViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}