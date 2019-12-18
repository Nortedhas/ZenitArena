package com.ageone.zenit.Modules.Sales

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import com.ageone.zenit.SCAG.Sale

class SalesViewModel : InterfaceViewModel {
    var model = SalesModel()

    enum class EventType {
        OnStockPressed
    }

    var realmData = listOf<Sale>()
    fun loadRealmData() {
        realmData = utils.realm.sale.getAllObjects()
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is SalesModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class SalesModel : InterfaceModel {

}
