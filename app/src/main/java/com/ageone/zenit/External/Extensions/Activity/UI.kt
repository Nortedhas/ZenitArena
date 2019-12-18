package com.ageone.zenit.External.Extensions.Activity

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.utils
import timber.log.Timber

fun Activity.hideKeyboard() {
    //hide keyboard
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)

}

fun Activity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}

fun Activity.setNavigationBarColor(color: Int) {
    window.navigationBarColor = color
}

fun Activity.setDisplaySize() {
    val displayMetrics = resources.displayMetrics

    utils.variable.displayWidthPx = displayMetrics.widthPixels
    utils.variable.displayHeightPx = displayMetrics.heightPixels

    utils.variable.displayDensity = displayMetrics.density

    Timber.i("width = ${utils.variable.displayWidthDp}")

    // Calculate ToolBar height
    val tv = TypedValue()
    if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        utils.variable.actionBarHeight =
            (TypedValue.complexToDimensionPixelSize(tv.data, displayMetrics) / displayMetrics.density).toInt()

    }
}

fun Activity.copyToClipboard(text: CharSequence){
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", text)
    clipboard.primaryClip = clip
}