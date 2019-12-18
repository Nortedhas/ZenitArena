package com.ageone.zenit.External.Extensions.Activity

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

fun Activity.setFullScreen() {
    //fullscreen flags
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS

    window.navigationBarColor = Color.TRANSPARENT
}

//if API less then 23 - background colorIcons is colorForDarkBack
fun Activity.setLightStatusBar(colorStatusBar: Int, colorForDarkBack: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = window.decorView.systemUiVisibility // get current flag
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR   // add LIGHT_STATUS_BAR to flag
        window.decorView.systemUiVisibility = flags
        window.statusBarColor = colorStatusBar
    } else {
        window.statusBarColor = colorForDarkBack
    }
}

//if API less then 23 - background colorIcons is colorForDarkBack
fun Activity.clearLightStatusBar(colorStatusBar: Int, colorForDarkBack: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = window.decorView.systemUiVisibility // get current flag
        flags =
                flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // use XOR here for remove LIGHT_STATUS_BAR from flags
        window.decorView.systemUiVisibility = flags
        window.statusBarColor = colorStatusBar
    } else {
        window.statusBarColor = colorForDarkBack
    }
}