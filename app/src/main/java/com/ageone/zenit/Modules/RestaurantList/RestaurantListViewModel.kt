package com.example.ageone.Modules.Restaurant

import com.ageone.zenit.Application.rxData
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Extensions.Activity.startLocation
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import com.ageone.zenit.External.Libraries.GoogleMap.distance
import com.ageone.zenit.Models.Filter
import com.ageone.zenit.SCAG.Product
import com.ageone.zenit.SCAG.User
import timber.log.Timber

class RestaurantListViewModel : InterfaceViewModel{
    var model = RestaurantListModel()

    enum class EventType {
        OnRestaurantPressed,
        OnFilterPressed,
        OnBannerPressed
    }

    var realmData = listOf<User>()
    fun loadRealmData() {
        realmData = when (rxData.currentFilter) {
            Filter.none -> {
                utils.realm.user.getAllObjects()
            }

            Filter.price -> {
                utils.realm.user.getAllObjects().sort("averageСheck")
            }

            Filter.distance -> {
                val shops = utils.realm.shop.getAllObjects()
                val distanceHash = mutableMapOf<String, Double>()

                shops.forEach { shop ->
                    var distanceToShop = .0

                    shop.location?.let { shopLocation ->
                        distanceToShop = distance(
                            startLocation.latitude,
                            startLocation.longitude,
                            shopLocation.latitude,
                            shopLocation.longitude,
                            "K"
                        )
                    }

                    if (distanceHash.containsKey(shop.ownerHashId)) {
                        distanceHash[shop.ownerHashId]?.let { currentDistance ->
                            if (currentDistance > distanceToShop) {
                                distanceHash[shop.ownerHashId] = distanceToShop
                            }
                        }

                    } else {
                        distanceHash[shop.ownerHashId] = distanceToShop
                    }

                    true

                }

                val companies = mutableListOf<User>()

                distanceHash.toList().sortedBy { it.second }.forEach { (hashId, diastance) ->
                    utils.realm.user.getObjectById(hashId)?.let { company ->
                        companies.add(company)
                    }
                }

                utils.realm.user.getAllObjects().forEach { companyInBase ->
                    if (!companies.contains(companyInBase)) {
                        companies.add(companyInBase)
                    }
                }

                companies
            }
        }

    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantListModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantListModel : InterfaceModel {

}
