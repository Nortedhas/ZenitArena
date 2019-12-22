package com.ageone.zenit.Application.Coordinator.Flow.Regular

import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.ModuleInterface
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Answer.AnswerModel
import com.ageone.zenit.Modules.Answer.AnswerView
import com.ageone.zenit.Modules.Answer.AnswerViewModel
import com.ageone.zenit.Modules.Quiz.QuizModel
import com.ageone.zenit.Modules.Quiz.QuizView
import com.ageone.zenit.Modules.Quiz.QuizViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowQuiz(previousFlow: BaseFlow) {

    var flow: FlowQuiz? = FlowQuiz(previousFlow)

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

    flow?.start()

}

class FlowQuiz(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowQuizModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleQuiz()
    }

    inner class FlowQuizModels {
        val modelQuizText = QuizModel()
        val modelAnswer = AnswerModel()
    }

    fun runModuleQuiz() {
        val module = QuizView(
            InitModuleUI(
                isBottomNavigationVisible = false
            )
        )

        module.viewModel.initialize(models.modelQuizText) { module.reload() }

        module.emitEvent = { event ->
            when (QuizViewModel.EventType.valueOf(event)) {
                QuizViewModel.EventType.OnAnswerPressed -> {
                    runModuleAnswer()
                }
            }
        }

        push(module)
    }

    fun runModuleAnswer() {
        val module = AnswerView(
            InitModuleUI(
                isBackPressed = true,
                isBottomNavigationVisible = false
            ))

        module.viewModel.initialize(models.modelAnswer) { module.reload() }

        module.emitEvent = {event ->
            when(AnswerViewModel.EventType.valueOf(event)) {

            }
        }

        push(module)
    }
}