package com.ageone.zenit.Modules.QR

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class QRViewModel : InterfaceViewModel {
    var model = QRModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is QRModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class QRModel : InterfaceModel {

}
