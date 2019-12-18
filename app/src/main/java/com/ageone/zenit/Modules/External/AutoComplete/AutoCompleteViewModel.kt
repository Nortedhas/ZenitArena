package com.ageone.zenit.Modules.External.AutoComplete

import com.ageone.zenit.Application.placesClient
import com.ageone.zenit.Application.router
import com.ageone.zenit.External.Base.SearchView.BaseSearchView
import com.ageone.zenit.External.Extensions.Activity.startLocation
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.Models.User.user
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import timber.log.Timber


class AutoCompleteViewModel : InterfaceViewModel {
    var model = AutoCompleteModel()

    enum class EventType {

    }

    var callbackOnItemSelected = TypeCallback.Back
    enum class TypeCallback {
        Back, Substitution
    }

    var realmData: MutableList<Answer> = mutableListOf<Answer>()
    fun loadRealmData() {
        realmData.clear()
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is AutoCompleteModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    val token = AutocompleteSessionToken.newInstance()
    fun getComplete(query: String) {

        // Create a RectangularBounds object.
        val bounds = RectangularBounds.newInstance(
            LatLng(
                startLocation.latitude - 0.5,
                startLocation.longitude - 0.5
            ),
            LatLng(
                startLocation.latitude + 0.5,
                startLocation.longitude + 0.5
            )
        )

        val request = FindAutocompletePredictionsRequest.builder()
            .setLocationBias(bounds)
            .setCountry("ru")
            .setTypeFilter(TypeFilter.ADDRESS)
            .setSessionToken(token)
            .setQuery(query)
            .build()

        placesClient?.let { placesClient ->
            placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener { response ->
                    realmData.clear()
                    for (prediction in response.autocompletePredictions) {
                        Timber.i("Vars: ${prediction.getPrimaryText(null)} - ${prediction.getSecondaryText(null)}")
                        realmData.add(
                            Answer(
                                prediction.getPrimaryText(null).toString(),
                                prediction.getSecondaryText(null).toString()
                            ))
                        RxBus.publish(EventComplete.EventChangeCompleteVariants())
                    }
                }.addOnFailureListener { exception ->
                    realmData.clear()
                    RxBus.publish(EventComplete.EventChangeCompleteVariants())
                }
        }
    }

    fun setCallback(answer: Answer, searchView: BaseSearchView){
        when (callbackOnItemSelected) {
            TypeCallback.Back -> {
                user.info.address = answer.primaryText
                RxBus.publish(RxEvent.EventChangeAddress())
                router.onBackPressed()
            }
            TypeCallback.Substitution -> {
                user.info.address = answer.primaryText
                searchView.setQuery(user.info.address, true)
            }
        }
    }
}

class AutoCompleteModel : InterfaceModel {

}

data class Answer(
    val primaryText: String = "",
    val secondaryText: String = ""
)

class EventComplete {
    class EventChangeCompleteVariants
}
