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
    var answerPosition: String by Delegates.observable("") { property, oldValue, newValue ->
        if(newValue != oldValue) {
            RxBus.publish(RxEvent.EventChangeAnswerPosition())
        }
    }
    var answerOffer: String by Delegates.observable("") { property, oldValue, newValue ->
        if(newValue != oldValue) {
            RxBus.publish(RxEvent.EventChangeAnswerOffer())
        }
    }

    var callAnswer: String by Delegates.observable("") { property, oldValue, newValue ->
        if(newValue != oldValue) {
            RxBus.publish(RxEvent.EventChangeCallAnswer())
        }
    }
}

class RxEvent {
    class EventChangeAddress
    data class EventLoadImage(val loadedImage: Image)
    class EventChangeAnswer
    class EventChangeAnswerPosition
    class EventChangeAnswerOffer
    class EventChangeCallAnswer
}
