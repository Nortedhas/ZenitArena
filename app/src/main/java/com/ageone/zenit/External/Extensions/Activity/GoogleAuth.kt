package com.ageone.zenit.External.Extensions.Activity

import com.ageone.zenit.Application.AppActivity
import com.ageone.zenit.External.Libraries.Alert.alertManager
import com.ageone.zenit.External.Libraries.Alert.blockUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import timber.log.Timber

fun AppActivity.setGoogleSignInClient() {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    val account = GoogleSignIn.getLastSignedInAccount(this)
}

fun handleSignInResult(completedTask: Task<GoogleSignInAccount>, completion: (email: String, name: String) -> Unit) {
    try {
        val account = completedTask.getResult(ApiException::class.java)
        if (account == null) {
            alertManager.blockUI(false)
            return
        }

        val email: String? = account.email
        if (email.isNullOrBlank()) {
            alertManager.blockUI(false)
            return
        }

        val name = account.displayName ?: ""

        // Signed in successfully
        completion.invoke(email, name)

    } catch (e: ApiException) {
        Timber.i("signInResult: failed code: ${e.statusCode}")
    }

}