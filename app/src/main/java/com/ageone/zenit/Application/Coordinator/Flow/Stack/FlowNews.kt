package com.ageone.zenit.Application.Coordinator.Flow.Stack

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
import com.ageone.zenit.Modules.Item.ItemModel
import com.ageone.zenit.Modules.Item.ItemView
import com.ageone.zenit.Modules.Item.ItemViewModel
import com.ageone.zenit.Modules.News.NewsView
import com.ageone.zenit.Modules.News.NewsModel
import com.ageone.zenit.Modules.News.NewsViewModel
import com.ageone.zenit.Modules.Quiz.QuizModel
import com.ageone.zenit.Modules.Quiz.QuizView
import com.ageone.zenit.Modules.Quiz.QuizViewModel
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

    private var models = FlowNewsModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleNews()
    }

    inner class FlowNewsModels {
        val modelNews = NewsModel()
        val modelItem = ItemModel()
        val modelQuiz = QuizModel()
        val modelAnswer = AnswerModel()
    }

    fun runModuleNews() {
        val module = NewsView()

        module.viewModel.initialize(models.modelNews) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (NewsViewModel.EventType.valueOf(event)) {
                NewsViewModel.EventType.OnContinuePressed -> {
                    runModuleItem()
                }
                NewsViewModel.EventType.OnQuizPressed -> {
                    runModuleQuiz()
                }
            }
        }
        push(module)
    }

    fun runModuleItem() {
        val module = ItemView(
            InitModuleUI(
            isBackPressed = true
        ))

        module.viewModel.initialize(models.modelItem) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ItemViewModel.EventType.valueOf(event)) {

            }
        }

        push(module)
    }

    fun runModuleQuiz() {
        val module = QuizView(
            InitModuleUI(
                isBackPressed = true,
                isBottomNavigationVisible =  false
        ))

        module.viewModel.initialize(models.modelQuiz) { module.reload() }

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

        module.viewModel.initialize(models.modelQuiz) { module.reload() }

        module.emitEvent = {event ->
            when(AnswerViewModel.EventType.valueOf(event)) {

            }
        }

        push(module)
    }

}