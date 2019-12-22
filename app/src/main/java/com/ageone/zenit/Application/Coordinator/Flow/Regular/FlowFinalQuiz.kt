package com.ageone.zenit.Application.Coordinator.Flow.Regular

import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.ModuleInterface
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.FinalQuiz.FinalQuizModel
import com.ageone.zenit.Modules.FinalQuiz.FinalQuizView
import com.ageone.zenit.Modules.FinalQuiz.FinalQuizViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowFinalQuiz(previousFlow: BaseFlow) {

    var flowFinal: FlowFinalQuiz? = FlowFinalQuiz(previousFlow)

    flowFinal?.let { flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

        Stack.flows.add(flow)
    }

    flowFinal?.onFinish = {
        flowFinal?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is ModuleInterface) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

        flowStorage.deleteFlow(flowFinal?.viewFlipperModule)
        flowFinal?.viewFlipperModule?.removeAllViews()
        flowFinal = null

    }

    flowFinal?.start()

}

class FlowFinalQuiz(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowFinalQuizModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleFinalQuiz()
    }

    inner class FlowFinalQuizModels {
        val modelFinalQuiz = FinalQuizModel()
    }

    fun runModuleFinalQuiz() {
        val module = FinalQuizView(
            InitModuleUI(
                isBottomNavigationVisible = false
            )
        )

        module.viewModel.initialize(models.modelFinalQuiz) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (FinalQuizViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}