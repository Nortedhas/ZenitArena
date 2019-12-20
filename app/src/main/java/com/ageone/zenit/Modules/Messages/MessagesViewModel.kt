package com.ageone.zenit.Modules.Messages

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class MessagesViewModel : InterfaceViewModel {
    var model = MessagesModel()

    enum class EventType {
        OnChatPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is MessagesModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MessagesModel : InterfaceModel {

}
