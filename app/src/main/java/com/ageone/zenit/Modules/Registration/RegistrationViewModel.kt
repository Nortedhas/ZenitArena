package com.ageone.zenit.Modules.Registration

import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import timber.log.Timber

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
