package com.ageone.zenit.Modules.Status

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import com.ageone.zenit.R

class StatusViewModel : InterfaceViewModel {
    var model = StatusModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is StatusModel) {
            model = recievedModel
            completion.invoke()
        }
    }

}

enum class Prizes(val matchNum: Int){
    Tape(4),
    Letter(5),
    Jacket(7),
    Hat(14),
    Backpack(21),
    Sweatshirt(28),
    Socks(35)
}
class StatusModel : InterfaceModel {
    val countMatches = 3
    var countEventsBeforePrize = -1
    var image = R.drawable.pic_status_7
}
