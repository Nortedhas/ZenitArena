package com.ageone.zenit.Modules.Photo

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class PhotoViewModel : InterfaceViewModel {
    var model = PhotoModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is PhotoModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class PhotoModel : InterfaceModel {

}
