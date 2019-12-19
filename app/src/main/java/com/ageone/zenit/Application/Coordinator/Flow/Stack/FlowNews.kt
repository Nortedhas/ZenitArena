package com.ageone.zenit.Application.Coordinator.Flow.Stack

import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.Module.ModuleInterface
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.News.NewsView
import com.ageone.zenit.Modules.News.NewsModel
import com.ageone.zenit.Modules.News.NewsViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowNews() {

    var flow: FlowNews? = FlowNews()

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

class FlowNews(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowFlowNewsModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleNews()
    }

    inner class FlowFlowNewsModels {
        val modelNews = NewsModel()
    }

    fun runModuleNews() {
        val module = NewsView()

        module.viewModel.initialize(models.modelNews) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (NewsViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}