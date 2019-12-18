package com.ageone.zenit.Modules.Registration

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class RegistrationViewModel : InterfaceViewModel {
    var model = RegistrationModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RegistrationModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RegistrationModel : InterfaceModel {

}
