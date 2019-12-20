package com.ageone.zenit.Modules.Item

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class ItemViewModel : InterfaceViewModel {
    var model = ItemModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ItemModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ItemModel : InterfaceModel {

}
