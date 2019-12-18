package com.ageone.zenit.Application

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.External.Base.Activity.BaseActivity
import com.ageone.zenit.External.Extensions.Activity.*
import com.ageone.zenit.External.Extensions.FlowCoordinator.clearFlowStack
import com.ageone.zenit.External.Extensions.FlowCoordinator.logout
import com.ageone.zenit.External.HTTP.update
import com.ageone.zenit.External.Utils.Validation.KeyParameterValidation
import com.ageone.zenit.External.Utils.Validation.isValidUser
import com.ageone.zenit.Models.User.user
import com.ageone.zenit.R
import com.ageone.zenit.SCAG.DataBase
import com.github.kittinunf.fuel.core.FuelManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import kotlinx.coroutines.*
import timber.log.Timber

class AppActivity: BaseActivity() {
    var mGoogleSignInClient: GoogleSignInClient? = null

    var fusedLocationClient: FusedLocationProviderClient? = null
    var locationRequest: LocationRequest? = null
    var locationCallback: LocationCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //only vertical mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        // MARK: GOOGLE

        setGoogleSignInClient()

        // MARK: UI

        setFullScreen()
        setDisplaySize()


        // MARK: PERMISSIONS

        addStoragePermissions()
        addLocationPermissions()
        verifyPermissions {
            if (hasPermissions(PERMISSIONS_LOCATION)) {
                user.permission.geo = true
                setLocationUpdates(1000, 1000)
            }

        }

        // MARK: start UI & request work

        start()

        setContentView(router.layout)
    }

    override fun onBackPressed() {
        router.onBackPressed()
    }


    override fun onPause() {
        super.onPause()
        if (user.permission.geo) {
            stopLocationUpdates()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()

        if (user.permission.geo) {
            startLocationUpdates()
        }

        if (webSocket.isStarted && !webSocket.socket.connected()) {
            webSocket.initialize()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        clearFlowStack()
        router.layout.removeAllViews()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResult: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResult.isNotEmpty() && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasPermissions(PERMISSIONS_LOCATION)) {
                        user.permission.geo = true
                        setLocationUpdates(1000, 1000)
                    } else {
                        Toast.makeText(this, "Location permission missing", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}
