package com.ageone.zenit.Internal.Utilities

import com.ageone.zenit.External.Libraries.Alert.alertManager
import com.ageone.zenit.External.Libraries.Alert.blockUI
import com.ageone.zenit.External.Libraries.Alert.single
import com.ageone.zenit.External.Utils.Tools
import com.ageone.zenit.External.Utils.Variable
import com.ageone.zenit.SCAG.ConfigDefault
import com.ageone.zenit.SCAG.RealmUtilities
import kotlin.properties.Delegates

class Utils {
    val tools = Tools
    val variable = Variable
    var realm = RealmUtilities
    val config = ConfigDefault
    val googleApiKey = "AIzaSyCDfj5ZoL0kncxgH8ja-julymHYjFR3Av0"

    var isNetworkReachable: Boolean by Delegates.observable(true) {property, oldValue, newValue ->
        if (oldValue == newValue) return@observable

        if (newValue) {

            // MARK: internet are allowed

            alertManager.blockUI(false)
        } else {

            // MARK: internet are not allowed

            alertManager.blockUI(true)
            alertManager.single(
                "Отсутствует интернет соединение",
                "В данный момент интернет соединение отстутсвует, либо соединение с сетью нестабильно")

        }
    }
}