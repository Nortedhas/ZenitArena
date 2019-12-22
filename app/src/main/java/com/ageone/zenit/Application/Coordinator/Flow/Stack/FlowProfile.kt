package com.ageone.zenit.Application.Coordinator.Flow.Stack

import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.ModuleInterface
import com.ageone.zenit.Modules.Profile.ProfileView
import com.ageone.zenit.Modules.Profile.ProfileModel
import com.ageone.zenit.Modules.Profile.ProfileViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowProfile() {

    var flowProfile: FlowProfile? = FlowProfile()

    flowProfile?.let { flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

        Stack.flows.add(flow)
    }

    flowProfile?.onFinish = {
        flowProfile?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is ModuleInterface) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

        flowStorage.deleteFlow(flowProfile?.viewFlipperModule)
        flowProfile?.viewFlipperModule?.removeAllViews()
        flowProfile = null

    }

//    flow?.start()

}

class FlowProfile(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowProfileModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleProfile()
    }

    inner class FlowProfileModels {
        val modelProfile = ProfileModel()
    }

    fun runModuleProfile() {
        val module = ProfileView()

        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}