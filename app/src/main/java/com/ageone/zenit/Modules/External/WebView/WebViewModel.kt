package com.ageone.zenit.Modules

import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class WebViewViewModel : InterfaceViewModel {
    var model = WebViewModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is WebViewModel) {
            model = recievedModel
            completion.invoke()
        }
    }
    enum class EventType {

    }
}

class WebViewModel : InterfaceModel {
    var url = ""
}
