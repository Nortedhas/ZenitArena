package com.ageone.zenit.Modules.Chat

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class ChatViewModel : InterfaceViewModel {
    var model = ChatModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChatModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ChatModel : InterfaceModel {

}
