package com.ageone.zenit.External.Base.Module

import android.view.View
import com.ageone.zenit.External.InitModuleUI

interface ModuleInterface {
    val idView: Int
    var initModuleUI: InitModuleUI

    var onDeInit: (() -> Unit)?
    var emitEvent: ((String) -> Unit)?

    fun className(): String
    fun getView(): View
}