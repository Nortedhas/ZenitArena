package com.ageone.zenit.Modules.Event

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class EventViewModel : InterfaceViewModel {
    var model = EventModel()

    enum class EventType {
        OnEventPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is EventModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class EventModel : InterfaceModel {

}
