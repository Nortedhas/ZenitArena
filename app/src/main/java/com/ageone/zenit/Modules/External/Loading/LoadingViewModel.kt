package com.ageone.zenit.Modules

import com.ageone.zenit.Application.AppActivity
import com.ageone.zenit.Application.api
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.Application.webSocket
import com.ageone.zenit.External.Extensions.Activity.fetchLastLocation
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import com.ageone.zenit.Models.User.user
import kotlinx.coroutines.*
import timber.log.Timber

class LoadingViewModel : InterfaceViewModel {
    var model = LoadingModel()

    enum class EventType{
        onFinish
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is LoadingModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    suspend fun startLoading() {
        runBlocking {
            val mainload = async {
                api.mainLoad()
            }

            if (user.permission.geo) {
                user.location.currentLocation = (currentActivity as? AppActivity)?.fetchLastLocation()
            }

            mainload.join()
        }

        webSocket.initialize()
    }
}

class LoadingModel : InterfaceModel {

}
