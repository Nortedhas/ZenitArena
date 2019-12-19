package com.ageone.zenit.Application

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.ageone.zenit.External.Base.Activity.BaseActivity
import com.ageone.zenit.External.Extensions.Activity.*
import com.ageone.zenit.External.Extensions.FlowCoordinator.clearFlowStack
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.Models.User.user
import com.ageone.zenit.R
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.*


const val REQUEST_GET_PHOTO = 5
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
        verifyPermissions {

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_GET_PHOTO -> {
                data?.let { data ->
                    GlobalScope.launch(Dispatchers.Main){
                        val image = api.uploadImage(data.getPath())
                        image ?: return@launch
                        RxBus.publish(RxEvent.EventLoadImage(image))
                    }
                }

            }
        }
    }

    private fun Intent.getPath(): String {
        val selectedImage: Uri = data
        val filePathColumn =
            arrayOf(MediaStore.Images.Media.DATA)

        val cursor: Cursor = contentResolver.query(
            selectedImage,
            filePathColumn, null, null, null
        )
        cursor.moveToFirst()

        val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
        val picturePath: String = cursor.getString(columnIndex)
        cursor.close()
        return picturePath
    }
}
