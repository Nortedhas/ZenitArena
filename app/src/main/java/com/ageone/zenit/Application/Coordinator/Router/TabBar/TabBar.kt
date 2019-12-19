package com.ageone.zenit.Application.Coordinator.Router.TabBar

import android.graphics.Color
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.currentFlow
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Flow.isBottomNavigationExist
import com.ageone.zenit.Application.Coordinator.Flow.setNavigationBarColor
import com.ageone.zenit.Application.Coordinator.Flow.setStatusBarColor
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack.flows
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack.items
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.Flow.FlowInterface
import com.ageone.zenit.External.Extensions.Activity.setLightStatusBar
import com.ageone.zenit.R
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import timber.log.Timber

object Stack {
    var flows = arrayListOf<FlowInterface>()
    var items = arrayListOf<AHBottomNavigationItem>()
}

object TabBar {

    fun createBottomNavigation() {
        isBottomNavigationExist = true

        Timber.i("Bottom nav: create bottom nav")

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected && position < flows.size) {
                flowStorage.displayFlow(position)
//                flowStorage.displayedChild = position

                //if flow starts in the first time
                if (!flows[position].isStarted) {
                    flows[position].start()
                }

                //correct image current module
                currentFlow = flows[position]
                setNavigationBarColor(flows[position].colorNavigationBar)

                if (flows[position].isLightStatusBar) {
                    currentActivity?.setLightStatusBar(
                        flows[position].colorStatusBar,
                        flows[position].colorStatusBarDark)

                } else {
                    setStatusBarColor(flows[position].colorStatusBar)
                }

            }
            true
        }

        createStackItem()
        setUpTabs()
    }

    val bottomNavigation by lazy {
        val bottomNavigation = AHBottomNavigation(currentActivity)
        bottomNavigation.setTitleTextSize(30f,30f)
        bottomNavigation.defaultBackgroundColor = Color.parseColor("#FEFEFE")//colorIcons
        bottomNavigation.isBehaviorTranslationEnabled = true
        bottomNavigation.accentColor = Color.parseColor("#00ACEB")
        bottomNavigation.inactiveColor = Color.parseColor("#D7D7D8")
        bottomNavigation.isForceTint = true
        bottomNavigation.isTranslucentNavigationEnabled = false
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNavigation
    }

    private fun setUpTabs() {

        bottomNavigation.removeAllItems()
        for (item in items) {
            bottomNavigation.addItem(item)
        }

    }

    private fun createStackItem() {
        items = arrayListOf(
            AHBottomNavigationItem("Новости", R.drawable.ic_lion),
            AHBottomNavigationItem("Сообщения", R.drawable.ic_messages),
            AHBottomNavigationItem("События", R.drawable.ic_events),
            AHBottomNavigationItem("Статус", R.drawable.ic_status),
            AHBottomNavigationItem("Профиль", R.drawable.ic_profile)
        )

    }
}

