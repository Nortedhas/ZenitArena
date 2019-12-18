package com.example.ageone.Modules.Entry

import com.ageone.zenit.Application.api
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import com.ageone.zenit.External.Libraries.Alert.alertManager
import com.ageone.zenit.External.Libraries.Alert.single
import com.ageone.zenit.External.Utils.Validation.isValidPhone
import com.ageone.zenit.External.Utils.Validation.toCorrectPhone
import com.ageone.zenit.Models.User.user

class RegistrationViewModel : InterfaceViewModel {
    var model = RegistrationModel()

    enum class EventType {
        OnNextPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RegistrationModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun validate(completion: () -> Unit){
        if(!model.phone.isValidPhone() || model.name.length < 3) {
            alertManager.single("Ошибка ввода", "Проверьте корректность введенных данных")
            return
        }

        completion.invoke()

    }
}

class RegistrationModel : InterfaceModel {
    var phone = ""
    var name = ""
}
