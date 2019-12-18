package com.ageone.zenit.Application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Router.Router
import com.ageone.zenit.BuildConfig
import com.ageone.zenit.External.Base.Activity.BaseActivity
import com.ageone.zenit.External.Extensions.Application.FTActivityLifecycleCallbacks
import com.ageone.zenit.External.HTTP.API.API
import com.ageone.zenit.Internal.Utilities.Utils
import com.ageone.zenit.Models.RxData
import com.ageone.zenit.Network.Socket.WebSocket
import com.ageone.zenit.R
import com.ageone.zenit.SCAG.DataBase
import com.github.kittinunf.fuel.core.FuelManager
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import net.alexandroid.shpref.ShPref
import timber.log.Timber


val router = Router()
val coordinator = FlowCoordinator()

val utils = Utils()
val api = API()
val rxData = RxData()
var webSocket = WebSocket()
var intent = Intent()

val currentActivity: BaseActivity?
    get() = App.instance?.mFTActivityLifecycleCallbacks?.currentActivity as? BaseActivity

var placesClient: PlacesClient? = null

class App: Application()  {

    init {
        instance = this
    }

    val mFTActivityLifecycleCallbacks = FTActivityLifecycleCallbacks()

    override fun onCreate() {
        super.onCreate()

        // MARK: SharePreferences

        ShPref.init(this, ShPref.APPLY)


        // MARK: Timber

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


        // MARK: reactive network

        ReactiveNetwork
            .observeNetworkConnectivity(applicationContext)
            .flatMapSingle{ ReactiveNetwork.checkInternetConnectivity()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{isConnected ->
                Timber.i("Internet: $isConnected")
                utils.isNetworkReachable = isConnected
            }


        // MARK: Realm

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)


        // MARK: fuel

//        FuelManager.instance.basePath = DataBase.url //TODO: add after server


        // MARK: current activity

        registerActivityLifecycleCallbacks(mFTActivityLifecycleCallbacks)


        // MARK: Google Places

        // Initialize the SDK
        Places.initialize(applicationContext, utils.googleApiKey)

        // Create a new Places client instance
        placesClient = Places.createClient(this)
    }

    companion object {

        var instance: App? = null

    }
}


val notificationId = 12345
val nameTabBar = "nameTabBar"

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        createNotificationChannel()

        val title = message.notification?.title ?: return
        val message = message.notification?.body ?: return
        Timber.i("Notification: $title: $message")

        sendNotification(title, message)
    }

    private fun sendNotification(notificationTitle: String, notificationContent: String) {

        val resultIntent = Intent(this, AppActivity::class.java)
        resultIntent.putExtra(nameTabBar, 1)

        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(resultIntent)
            editIntentAt(0)?.putExtra(nameTabBar, 1)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(notificationTitle)
            .setContentText(notificationContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(resultPendingIntent)
            .setChannelId(CHANNEL_ID)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, notificationBuilder.build())
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, "nahodka channel", importance)
            channel.enableLights(true)
            channel.enableVibration(true)
            channel.lightColor = Color.GRAY
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "firebase_playground_channel_id"
    }
}



