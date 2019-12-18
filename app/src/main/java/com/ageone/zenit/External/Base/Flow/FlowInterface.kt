package com.ageone.zenit.External.Base.Flow

import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.External.Base.Module.ModuleInterface

interface FlowInterface {
    //modules in flow
    val stack: MutableList<Int>

    //UserData for correct routing
    var settingsCurrentFlow: DataFlow
    var previousFlow: FlowInterface?

    var isStarted: Boolean
    var onStart: (() -> Unit)?
    var onFinish: (() -> Unit)?

    var isLightStatusBar: Boolean
    var colorStatusBar: Int
    var colorStatusBarDark: Int

    var colorNavigationBar: Int

    fun start()

    fun push(module: ModuleInterface?)
    fun pop()
    fun popToRoot()

}