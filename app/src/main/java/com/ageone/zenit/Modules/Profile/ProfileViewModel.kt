package com.ageone.zenit.Modules.Profile

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class ProfileViewModel : InterfaceViewModel {
    var model = ProfileModel()

    enum class EventType {
        OnQrCodePressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ProfileModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ProfileModel : InterfaceModel {

}
