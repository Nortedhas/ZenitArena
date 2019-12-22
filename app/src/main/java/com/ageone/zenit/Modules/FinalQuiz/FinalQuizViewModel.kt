package com.ageone.zenit.Modules.FinalQuiz

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class FinalQuizViewModel : InterfaceViewModel {
    var model = FinalQuizModel()

    enum class EventType {
        OnAnswerPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is FinalQuizModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class FinalQuizModel : InterfaceModel {

}
