package com.ageone.zenit.Modules.Registration

import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import com.ageone.zenit.External.Libraries.Alert.alertManager
import com.ageone.zenit.External.Libraries.Alert.single

class RegistrationViewModel : InterfaceViewModel {
    var model = RegistrationModel()

    enum class EventType {

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

    fun validate(completion: () -> Unit) {
        if(model.firstName.length < 3 ||
            model.secondName.length < 3 ||
            model.lastName.length < 3 ||
            model.gender.isBlank() ||
            model.birthDate == 0 ||
            model.birthPlace.length < 3 ||
            model.email.length < 3 ||
            model.phone.length < 18 ||
            model.passportNum == 0 ||
            model.passportIssue.length < 3 ||
            model.snilsNum == 0 ||
            model.innNum == 0 ||
            model.careerPlace.length < 3 ||
            model.jacketSize.isEmpty()
        ){
            alertManager.single("Ошибка ввода", "Проверьте корректность введения данных")
            return
        }


    }
}

class RegistrationModel : InterfaceModel {
    var firstName = ""
    var secondName = ""
    var lastName = ""
    var gender = ""
    var birthDate = 0
    var birthPlace = ""
    var email = ""
    var phone = ""
    var passportNum = 0
    var passportIssue = ""
    var snilsNum = 0
    var innNum = 0
    var careerPlace = ""
    var jacketSize = ""
}
