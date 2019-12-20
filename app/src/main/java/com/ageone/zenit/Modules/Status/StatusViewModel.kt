package com.ageone.zenit.Modules.Status

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class StatusViewModel : InterfaceViewModel {
    var model = StatusModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is StatusModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class StatusModel : InterfaceModel {

}
