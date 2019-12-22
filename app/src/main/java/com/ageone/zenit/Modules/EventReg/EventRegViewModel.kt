package com.ageone.zenit.Modules.EventReg

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class EventRegViewModel : InterfaceViewModel {
    var model = EventRegModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is EventRegModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class EventRegModel : InterfaceModel {

}
