package com.ageone.zenit.Application.Coordinator.Flow.Stack

import android.graphics.Color
import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.ModuleInterface
import com.ageone.zenit.External.Extensions.Activity.setLightStatusBar
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Event.EventModel
import com.ageone.zenit.Modules.Event.EventView
import com.ageone.zenit.Modules.Event.EventViewModel
import com.ageone.zenit.Modules.EventItem.EventItemModel
import com.ageone.zenit.Modules.EventItem.EventItemView
import com.ageone.zenit.Modules.EventItem.EventItemViewModel
import com.ageone.zenit.Modules.EventMap.EventMapModel
import com.ageone.zenit.Modules.EventMap.EventMapView
import com.ageone.zenit.Modules.EventMap.EventMapViewModel
import com.ageone.zenit.Modules.EventReg.EventRegModel
import com.ageone.zenit.Modules.EventReg.EventRegView
import com.ageone.zenit.Modules.EventReg.EventRegViewModel
import com.ageone.zenit.Modules.Photo.PhotoModel
import com.ageone.zenit.Modules.Photo.PhotoView
import com.ageone.zenit.Modules.Photo.PhotoViewModel
import com.ageone.zenit.Modules.Timing.TimingModel
import com.ageone.zenit.Modules.Timing.TimingView
import com.ageone.zenit.Modules.Timing.TimingViewModel

import timber.log.Timber

fun FlowCoordinator.runFlowEvent() {

    var flow: FlowEvent? = FlowEvent()

    flow?.let { flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

        currentActivity?.setLightStatusBar(Color.TRANSPARENT, Color.GRAY)

        flow.isLightStatusBar = true
        flow.colorStatusBar = Color.TRANSPARENT
        flow.colorStatusBarDark = Color.GRAY

        flow.colorNavigationBar = Color.BLACK

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

class FlowEvent(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowEventModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleEvent()
    }

    inner class FlowEventModels {
        val modelEvent = EventModel()
        val modelEventItem = EventItemModel()
        val modelTiming = TimingModel()
        val modelEventMap = EventMapModel()
        val modelPhoto = PhotoModel()
        val modelEventReg = EventRegModel()
    }

    fun runModuleEvent() {
        val module = EventView()

        module.viewModel.initialize(models.modelEvent) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (EventViewModel.EventType.valueOf(event)) {
                EventViewModel.EventType.OnEventPressed -> {
                    runModuleEventItem()
                }
            }
        }
        push(module)
    }

    fun runModuleEventItem() {
        val module = EventItemView(
            InitModuleUI(
                isBackPressed = true
        ))

        module.viewModel.initialize(models.modelEventItem) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (EventItemViewModel.EventType.valueOf(event)) {
                EventItemViewModel.EventType.OnTimingPressed -> {
                    runModuleTiming()
                }
                EventItemViewModel.EventType.OnMapPressed -> {
                    runModuleEventMap()
                }
                EventItemViewModel.EventType.OnRegistrationPressed -> {
                    runModuleEventReg()
                }
            }
        }

        push(module)
    }

    fun runModuleTiming() {
        val module = TimingView(
            InitModuleUI(
                isBackPressed = true
        ))

        module.viewModel.initialize(models.modelTiming) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when(TimingViewModel.EventType.valueOf(event)) {

            }
        }

        push(module)
    }

    fun runModuleEventMap() {
        val module = EventMapView(
            InitModuleUI(
                isBackPressed = true
        ))

        module.viewModel.initialize(models.modelEventMap) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when(EventMapViewModel.EventType.valueOf(event)) {
                EventMapViewModel.EventType.OnPhotoPressed -> {
                    runModulePhoto()
                }
            }
        }

        push(module)
    }

    fun runModulePhoto() {
        val module = PhotoView(
            InitModuleUI(
                isBackPressed = true,
                isBottomNavigationVisible = false
        ))

        module.viewModel.initialize(models.modelPhoto) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(PhotoViewModel.EventType.valueOf(event)) {

            }
        }

        push(module)
    }

    fun runModuleEventReg() {
        val module = EventRegView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            ))

        module.viewModel.initialize(models.modelEventReg) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(EventRegViewModel.EventType.valueOf(event)) {

            }
        }

        push(module)
    }
}