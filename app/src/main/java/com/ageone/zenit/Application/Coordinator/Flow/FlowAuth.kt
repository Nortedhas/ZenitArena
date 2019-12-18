package com.ageone.zenit.Application.Coordinator.Flow

import android.graphics.Color
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.coordinator
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Extensions.Activity.setLightStatusBar
import com.ageone.zenit.External.InitModuleUI
import com.example.ageone.Modules.Entry.RegistrationModel
import com.example.ageone.Modules.Entry.RegistrationView
import com.example.ageone.Modules.Entry.RegistrationViewModel

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ flow ->

        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        currentActivity?.setLightStatusBar(Color.WHITE, Color.GRAY)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

    }

    flow?.onFinish = {
        flowStorage.deleteFlow(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }


    flow?.start()

}

class FlowAuth: BaseFlow() {

    private var models = FlowAuthModels()

    override fun start() {
        onStarted()
        runModuleRegistration()

    }

    inner class FlowAuthModels {
        var modelRegistration = RegistrationModel()

    }

    fun runModuleRegistration() {
        val module = RegistrationView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = false
        ))

        module.emitEvent = { event ->
            when(RegistrationViewModel.EventType.valueOf(event)){
                RegistrationViewModel.EventType.OnNextPressed -> {
                    module.startLoadingFlow()
                }
            }
        }

        module.viewModel.initialize(models.modelRegistration) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }

    fun BaseModule.startLoadingFlow() {
        coordinator.start()
        onDeInit?.invoke()
    }
}