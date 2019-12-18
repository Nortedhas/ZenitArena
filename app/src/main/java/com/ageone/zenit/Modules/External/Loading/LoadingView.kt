package com.ageone.zenit.Modules

import android.graphics.Color
import com.ageone.zenit.R
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.InitModuleUI
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import yummypets.com.stevia.subviews

class LoadingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = LoadingViewModel()

    init {
        setBackgroundColor(Color.TRANSPARENT)

        innerContent.subviews(
        )

    }

    fun loading(){
        runBlocking {
            launch {
//                viewModel.startLoading() //TODO: add after server
            }.join()
        }

        emitEvent?.invoke(LoadingViewModel.EventType.onFinish.name)
    }



}
