package com.ageone.zenit.External.Base.Module

import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.ageone.zenit.Application.Coordinator.Flow.isBottomNavigationExist
import com.ageone.zenit.External.Base.Toolbar.BaseToolbar
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ConstraintLayout.BaseConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.External.Extensions.Activity.hideKeyboard
import com.ageone.zenit.External.InitModuleUI
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import yummypets.com.stevia.*


open class BaseModule(override var initModuleUI: InitModuleUI = InitModuleUI()): ConstraintLayout(currentActivity),
    ModuleInterface {
    override fun getView(): View = this

    override val idView: Int
        get() = id


    val backgroundFullscreen by lazy {
        val view = BaseConstraintLayout()
        view.backgroundColor = Color.TRANSPARENT
        view.initialize()
        view
    }

    private val content by lazy {
        val innerContent = BaseConstraintLayout()
        innerContent.setPadding(0, utils.variable.statusBarHeight, 0, 0)
        innerContent
    }

    val innerContent by lazy {
        val innerContent = BaseConstraintLayout()
        innerContent
    }

    val toolbar by lazy {
        val toolBar = BaseToolbar(initModuleUI, content)

        toolBar
            .setBackgroundColor(initModuleUI.colorToolbar)

        if (initModuleUI.isToolbarHidden) {
            toolBar.visibility = View.GONE
        }

        toolBar
    }

    val viewManagerBodyTable by lazy {
        val viewManager = LinearLayoutManager(currentActivity)
        viewManager
    }

    val bodyTable by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView.layoutManager = viewManagerBodyTable
        recyclerView
    }

    override var onDeInit: (() -> Unit)? = null
    override var emitEvent: ((String) -> Unit)? = null

    init {
        id = View.generateViewId()
        renderUI()

        currentActivity?.hideKeyboard()
        Timber.i("${this.className()} Init ")
    }

    var compositeDisposable = CompositeDisposable()

    fun unBind() {
        compositeDisposable.dispose()
    }

    fun renderUI() {
        subviews(
            backgroundFullscreen,
            content.subviews(
                toolbar,
                innerContent
            )
        )

        content
            .fillHorizontally()
            .fillVertically()

        toolbar
            .constrainTopToTopOf(content, 0)
            .fillHorizontally()
            .height(utils.variable.actionBarHeight)

            //TODO: replace in base

        val bottomMarginInnerContent =
            utils.variable.navigationBarHeightDp +
                    if (initModuleUI.isBottomNavigationVisible) 56F else 0F

        innerContent
            .fillHorizontally()
            .fillVertically()
            .constrainTopToBottomOf(toolbar)
            .constrainBottomToBottomOf(content, bottomMarginInnerContent.toInt())

        backgroundFullscreen
            .fillHorizontally()
            .fillVertically()

    }

    fun renderToolbar() {
        toolbar.initialize()
    }

    fun renderBodyTable() {
        innerContent.subviews(
            bodyTable
        )

        bodyTable
            .fillHorizontally()
            .fillVertically()
            .constrainTopToTopOf(innerContent)
            .updatePadding(bottom = 24.dp)

        bodyTable
            .clipToPadding = false

    }

    open fun reload() {
        bodyTable.adapter?.notifyDataSetChanged()
    }

    override fun className(): String {
        return utils.tools.getClassName(this.toString())
    }
}
