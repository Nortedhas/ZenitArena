package com.ageone.zenit.Modules.Restaurant

import com.ageone.zenit.Application.rxData
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Interfaces.InterfaceModel
import com.ageone.zenit.External.Interfaces.InterfaceViewModel
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.SCAG.Category
import com.ageone.zenit.SCAG.Product
import com.ageone.zenit.SCAG.Sale
import com.ageone.zenit.SCAG.User
import kotlinx.coroutines.*
import timber.log.Timber

class RestaurantViewModel : InterfaceViewModel {
    var model = RestaurantModel()

    enum class EventType {
        OnInfoPressed,
        OnReviewPressed
    }

    var realmData = mutableListOf<ProductForBucket>()
    fun loadRealmData() {
        runBlocking {
            launch {
                realmData.clear()

                model.categories = rxData.currentCompany?.createCategoriesFromCompany() ?: listOf()
                model.categories.forEach { category ->
                    Timber.i("Category: ${category.name}")
                }

                val currentProducts: List<Product> = rxData.currentCompany?.products?.filter { product ->
                    product.category?.let {currentCategory ->
                        if (model.currentCategory in model.categories.indices) {
                            currentCategory.hashId == model.categories[model.currentCategory].hashId
                        } else {
                            false
                        }
                    } ?: false
                } ?: emptyList()

                val sales = utils.realm.sale.getAllObjects()

                currentProducts.forEach { product ->
                    var priceWithSale = product.price

                    sales.forEach { sale ->
                        if (sale.product?.hashId == product.hashId) {
                            priceWithSale = product.price * (100 - sale.discount) / 100
                        }
                    }

                    realmData.add(
                        ProductForBucket(
                            product,
                            priceWithSale
                        )
                    )

                }
            }.join()

            model.categories.forEach { category ->
                Timber.i("Category: ${category.name}")
            }

            RxBus.publish(RxEvent.EventLoadCategories())
        }

    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantModel : InterfaceModel {
    var categories = listOf<Category>()
    var currentCategory = 0
}

fun User.createCategoriesFromCompany(): List<Category> {
    val set = mutableSetOf<Category>()

    products.forEach { product ->
        product.category?.let { category ->
            set.add(category)
        }
    }

    return set.toList()
}

data class ProductForBucket (
    var product: Product,
    var priceWithSale: Int
)