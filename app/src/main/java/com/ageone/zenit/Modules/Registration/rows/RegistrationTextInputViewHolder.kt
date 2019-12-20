package com.ageone.zenit.Modules.Registration.rows

import android.graphics.Color
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber
import yummypets.com.stevia.*

class RegistrationTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputRegistration by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#CBCBCB")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
        textInput.setBoxCornerRadii(12F,12F,12F,12F)

        textInput.editText?.let { editText ->

            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 20F
            editText.maxLines = 1
            editText.setSingleLine(true)
            Timber.i("editText.marginLeft:${editText.marginLeft}")
        }
        textInput
    }

    init {

        renderUI()
    }

}

fun RegistrationTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputRegistration
    )

    textInputRegistration
        .constrainTopToTopOf(constraintLayout,25)
        .fillHorizontally(16)
        .height(55)
        .setPadding(6.dp,0,0,0)
}

fun RegistrationTextInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputRegistration.hint = hint
    textInputRegistration.defineType(type)
}
