package com.ageone.zenit.Modules.EventItem

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class EventItemViewModel : InterfaceViewModel {
    var model = EventItemModel()

    enum class EventType {
        OnTimingPressed,
        OnMapPressed,
        OnRegistrationPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is EventItemModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class EventItemModel : InterfaceModel {

}
