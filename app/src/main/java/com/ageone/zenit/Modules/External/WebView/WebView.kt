package com.ageone.zenit.Modules

import android.graphics.Color
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ageone.zenit.R
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.InitModuleUI
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.fillVertically
import yummypets.com.stevia.subviews

class WebView(initModuleUI: InitModuleUI = InitModuleUI(), url: String) : BaseModule(initModuleUI) {
    val webView by lazy {
        val view = WebView(currentActivity)
        view
    }

    init {
        setBackgroundColor(Color.WHITE)
        toolbar.title = "Оплата"
        renderToolbar()

        innerContent.subviews(
            webView
        )

        webView
            .fillHorizontally()
            .fillVertically()

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        webView.webViewClient = MyWebViewClient()
    }
}

class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }
}
