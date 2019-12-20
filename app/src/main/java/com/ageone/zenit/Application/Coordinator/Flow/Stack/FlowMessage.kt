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
import com.ageone.zenit.Modules.Chat.ChatModel
import com.ageone.zenit.Modules.Chat.ChatView
import com.ageone.zenit.Modules.Chat.ChatViewModel
import com.ageone.zenit.Modules.Messages.MessagesView
import com.ageone.zenit.Modules.Messages.MessagesModel
import com.ageone.zenit.Modules.Messages.MessagesViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowMessage() {

    var flow: FlowMessage? = FlowMessage()

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

class FlowMessage(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowMessageModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleMessages()
    }

    inner class FlowMessageModels {
        val modelMessages = MessagesModel()
        val modelChat = ChatModel()
    }

    fun runModuleMessages() {
        val module = MessagesView()

        module.viewModel.initialize(models.modelMessages) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (MessagesViewModel.EventType.valueOf(event)) {
                MessagesViewModel.EventType.OnChatPressed -> {
                    runModuleChat()
                }
            }
        }
        push(module)
    }

    fun runModuleChat() {
        val module = ChatView(InitModuleUI(
            isBackPressed = true,
            isBottomNavigationVisible = false
        ))
        module.viewModel.initialize(models.modelChat) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (ChatViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}