package com.ageone.zenit.External.Utils

import android.graphics.Color
import com.ageone.zenit.Application.utils
import timber.log.Timber
import kotlin.properties.Delegates

object Tools {
    fun hex(hex: String): Int {
        return Color.parseColor("#$hex")
    }

    fun getClassName(name: String): String {
        return name.split("{")[0]
    }

    fun getActualSizeFromDes(size: Int) = utils.variable.displayWidthDp * (size / utils.variable.widthDisplayDesign)
}

object Variable {
    var statusBarHeight = 0
    var actionBarHeight = 0
    var navigationBarHeight by Delegates.vetoable(0) {property, oldValue, newValue ->
        Timber.i("Change nav bar height: $oldValue -> $newValue")
        oldValue == 0
    }

    var displayDensity: Float = 0F

    var displayWidthPx = 0
    var displayHeightPx = 0

    val displayWidthDp: Int
        get() {
            return (displayWidthPx / displayDensity).toInt()
        }

    val displayHeightDp: Int
        get() {
            return (displayHeightPx / displayDensity).toInt()
        }

    val navigationBarHeightDp: Int
        get() {
            return (navigationBarHeight / displayDensity).toInt()
        }

    var token: String by Delegates.observable(""){property, oldValue, newValue ->
        Timber.i("Token: $newValue")
    }

    val widthDisplayDesign = 375F
}