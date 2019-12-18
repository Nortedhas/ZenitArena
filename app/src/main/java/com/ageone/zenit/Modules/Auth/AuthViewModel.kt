package com.ageone.zenit.Modules.Auth

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class AuthViewModel : InterfaceViewModel {
    var model = AuthModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is AuthModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class AuthModel : InterfaceModel {

}
