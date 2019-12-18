package com.ageone.zenit.External.Extensions.FlowCoordinator

import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.Application.api
import com.ageone.zenit.Models.User.user
import io.realm.Realm
import timber.log.Timber


fun FlowCoordinator.logout() {
    Timber.i("Auth logout")
    //correct data
    user.isAuthorized = false
    api.cashTime = 0

    //clear stack flows
    clearFlowStack()

    //clear realm
    Realm.getDefaultInstance().executeTransaction { realm ->
        realm.deleteAll()
    }

    //restart flow
    start()
}

fun clearFlowStack() {
    Stack.flows.forEach { flow ->
        flow.onFinish?.invoke()
    }
    Stack.flows.clear()
}