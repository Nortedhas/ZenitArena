package com.ageone.zenit.Modules.EventMap

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class EventMapViewModel : InterfaceViewModel {
    var model = EventMapModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is EventMapModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class EventMapModel : InterfaceModel {

}
