package com.ageone.zenit.Modules.Answer

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class AnswerViewModel : InterfaceViewModel {
    var model = AnswerModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is AnswerModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class AnswerModel : InterfaceModel {
    var answer = ""
    var answerPosition = ""
    var answerOffer = ""
}
