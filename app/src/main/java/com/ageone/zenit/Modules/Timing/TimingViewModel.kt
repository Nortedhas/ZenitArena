package com.ageone.zenit.Modules.Timing

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class TimingViewModel : InterfaceViewModel {
    var model = TimingModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is TimingModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class TimingModel : InterfaceModel {

}
