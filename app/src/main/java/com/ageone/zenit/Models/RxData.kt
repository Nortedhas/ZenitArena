package com.ageone.zenit.Models

import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.SCAG.Image
import com.ageone.zenit.SCAG.Product
import com.ageone.zenit.SCAG.User
import timber.log.Timber
import kotlin.properties.Delegates


class RxData {
    var answer: String by Delegates.observable("") { property, oldValue, newValue ->
        if(newValue != oldValue) {
            RxBus.publish(RxEvent.EventChangeAnswer())
        }
    }
}

class RxEvent {
    class EventChangeAddress
    data class EventLoadImage(val loadedImage: Image)
    class EventChangeAnswer
}
