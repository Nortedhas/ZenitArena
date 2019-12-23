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
import com.ageone.zenit.Modules.Auth.AuthModel
import com.ageone.zenit.Modules.Auth.AuthView
import com.ageone.zenit.Modules.Auth.AuthViewModel
import com.ageone.zenit.Modules.Registration.RegistrationModel
import com.ageone.zenit.Modules.Registration.RegistrationView
import com.ageone.zenit.Modules.Registration.RegistrationViewModel

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ flow ->

        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        currentActivity?.setLightStatusBar(Color.TRANSPARENT, Color.GRAY)

        flow.isLightStatusBar = true
        flow.colorStatusBar = Color.TRANSPARENT
        flow.colorStatusBarDark = Color.GRAY

        flow.colorNavigationBar = Color.BLACK

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
        runModuleAuth()

    }

    inner class FlowAuthModels {
        val modelAuth = AuthModel()
        val modelRegistration = RegistrationModel()

    }
    fun runModuleAuth() {
        val module = AuthView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = false
        ))
        module.viewModel.initialize(models.modelAuth) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (AuthViewModel.EventType.valueOf(event)) {
                AuthViewModel.EventType.OnEnterPressed -> {
                    module.startLoadingFlow()
                }
                AuthViewModel.EventType.OnRegistrationPressed -> {
                    runModuleRegistration()
                }
            }
        }

        push(module)
    }

    fun runModuleRegistration() {
        val module = RegistrationView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = false
        ))

        module.viewModel.initialize(models.modelRegistration) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (RegistrationViewModel.EventType.valueOf(event)) {

            }
        }

        push(module)
    }

    fun BaseModule.startLoadingFlow() {
        coordinator.start()
        onDeInit?.invoke()
    }
}