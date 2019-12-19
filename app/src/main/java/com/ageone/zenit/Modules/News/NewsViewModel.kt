package com.ageone.zenit.Modules.News

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel

class NewsViewModel : InterfaceViewModel {
    var model = NewsModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is NewsModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class NewsModel : InterfaceModel {

}
