package com.ageone.zenit.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Flow.Regular.runFlowAddress
import com.ageone.zenit.Application.Coordinator.Flow.setNavigationBarColor
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.Application.coordinator
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Icon
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Profile.ProfileModel
import com.ageone.zenit.Modules.Profile.ProfileView
import com.ageone.zenit.Modules.Profile.ProfileViewModel
import com.ageone.zenit.R
import timber.log.Timber

fun FlowCoordinator.runFlowProfile() {

    var flow: FlowProfile? = FlowProfile()

    flow?.let { flow ->
        setNavigationBarColor(Color.GREEN)

        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)
        flow.colorStatusBar = Color.parseColor("#21D5BF")
        flow.colorNavigationBar = Color.BLUE

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

class FlowProfile : BaseFlow() {

    private var models = FlowProfileModels()

    override fun start() {
        onStarted()
        runModuleProfile()
    }

    inner class FlowProfileModels {
        val modelProfile = ProfileModel()
    }

    fun runModuleProfile() {
        val module = ProfileView(InitModuleUI(
            firstIcon = Icon(
                icon = R.drawable.ic_cross,
                size = 30,
                listener = {
                }
            )
        )
        )

        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {
                ProfileViewModel.EventType.OnFillAddressPressed -> {
                    coordinator.runFlowAddress(this)
                }
            }
        }
        push(module)
    }

}