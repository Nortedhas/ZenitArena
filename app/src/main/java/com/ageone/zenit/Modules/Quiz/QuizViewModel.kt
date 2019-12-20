package com.ageone.zenit.Modules.Quiz

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class QuizViewModel : InterfaceViewModel {
    var model = QuizModel()

    enum class EventType {
        OnAnswerPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is QuizModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class QuizModel : InterfaceModel {

}
