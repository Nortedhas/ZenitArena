package com.ageone.zenit.External.Extensions.Activity

import com.ageone.zenit.Application.*
import com.ageone.zenit.Application.Coordinator.Router.TabBar.TabBar.bottomNavigation
import com.ageone.zenit.External.Base.Activity.BaseActivity
import com.ageone.zenit.External.Extensions.FlowCoordinator.logout
import com.ageone.zenit.External.HTTP.update
import com.ageone.zenit.External.Utils.Validation.KeyParameterValidation
import com.ageone.zenit.External.Utils.Validation.isValidUser
import com.ageone.zenit.Models.User.user
import com.ageone.zenit.SCAG.DataBase
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import yummypets.com.stevia.constrainBottomToBottomOf
import java.util.*
import kotlin.concurrent.schedule

fun BaseActivity.start() {
    coordinator.setLaunchScreen()

    setStatusBarHeight().then {
        router.layout.setOnApplyWindowInsetsListener { _, insets ->
            insets
        }
        startChecks()

        coordinator.updateBottomMargins()
    }
}

private fun BaseActivity.startChecks() {
    //repeat handshake every N = 6 hours
    Timer().schedule(0, 60_000 * 60 * 6) {
        runBlocking {
            async {
//                api.handshake() //TODO: add after server
            }.join()
        }
    }.run()

    //check if server return empty user
    if (!isValidUser(KeyParameterValidation.email)) {
        coordinator.logout()
    }

    //if Google API success - start flow
    checkGoogleAPI {
        coordinator.start()
    }

    setFirebaseToken()
}

private fun BaseActivity.checkGoogleAPI(completionOnSuccess: ()->Unit) {
    val googleApiAvailability = GoogleApiAvailability.getInstance()
    when (val result = googleApiAvailability.isGooglePlayServicesAvailable(currentActivity)) {
        ConnectionResult.SUCCESS -> {
            completionOnSuccess.invoke()
        }
        else -> {
            googleApiAvailability.showErrorNotification(currentActivity, result)
        }
    }
}

private fun BaseActivity.setFirebaseToken() {
    FirebaseInstanceId.getInstance().instanceId
        .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.i("Fail to get FirebaseInstanceId")
                return@OnCompleteListener
            }

            // set fcm token if it exists
            task.result?.token?.let { token ->
//                DataBase.User.update(user.hashId, mapOf("fcmToken" to token)) //TODO: add after server
                user.fcmToken = token
            }
        })
}

private fun BaseActivity.setStatusBarHeight() = Promise<Unit> { resolve, _ ->

    router.layout.setOnApplyWindowInsetsListener { _, insets ->
        utils.variable.statusBarHeight = insets.systemWindowInsetTop
        utils.variable.navigationBarHeight = insets.systemWindowInsetBottom

        Timber.i("navigationBarHeight: ${utils.variable.navigationBarHeight}")

        resolve(kotlin.Unit)
        insets
    }
}