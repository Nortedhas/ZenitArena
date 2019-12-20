package com.ageone.zenit.External.Base.TextInputLayout

import android.content.res.ColorStateList
import android.os.Handler
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.updateMargins
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.EditText.limitLength
import com.ageone.zenit.External.Base.EditText.phoneMask
import com.ageone.zenit.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.dp
import yummypets.com.stevia.style
import kotlin.properties.Delegates


class BaseTextInputLayout: TextInputLayout(currentActivity) {

    var inactiveHintColor by Delegates.observable(0){property, oldValue, newValue ->
        val states = arrayOf(intArrayOf())
        val colors = intArrayOf(newValue)
        defaultHintTextColor  = ColorStateList(states,colors)
    }

    init {
        style {
            setHintTextAppearance(R.style.MyHintText)
            setErrorTextAppearance(R.style.ErrorText)
        }

        val text = BaseTextInputEditText()
        addView(text)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        layoutParams = params

        editText?.let { editText ->

            editText.setOnTouchListener { view, motionEvent ->
                if(motionEvent.action == KeyEvent.ACTION_DOWN ){
                    Handler().postDelayed({
                        editText.requestFocus()
                    }, 500)
                }
                false
            }
        }

    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams?) {
        if (child is EditText) {
            (child as EditText).textSize = 14f
        }
        super.addView(child, index, params)
    }

    fun setToggleForPassword(colorToggled: Int) {
        style {
            isPasswordVisibilityToggleEnabled = true
            setPasswordVisibilityToggleTintList(ColorStateList.valueOf(colorToggled))
        }
    }

    fun defineType (type: InputEditTextType) = when(type) {

        InputEditTextType.EMAIL -> {
            editText?.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        }

        InputEditTextType.NUMERIC -> {
            editText?.inputType = InputType.TYPE_CLASS_NUMBER
        }

        InputEditTextType.URI -> {
            editText?.inputType = InputType.TYPE_TEXT_VARIATION_URI
        }

        InputEditTextType.PHONE -> {
            editText?.let { editText ->
                editText.inputType = InputType.TYPE_CLASS_NUMBER
                editText.keyListener = DigitsKeyListener.getInstance("1234567890")
                editText.limitLength(18)

                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        if (before == 0) {
                            val newPhone = editText.phoneMask(s)
                            editText.setText(newPhone)
                            editText.setSelection(editText.text?.length ?: 0)
                        }
                    }
                })
            }
        }

        InputEditTextType.PASSWORD -> {
            editText?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        InputEditTextType.TEXT -> {}
    }

    fun setInactiveUnderlineColor(color: Int) {
        editText?.backgroundTintList = ColorStateList.valueOf(color)
    }

}

class BaseTextInputEditText: TextInputEditText(currentActivity) {
}

enum class InputEditTextType{
    TEXT, NUMERIC, EMAIL, URI, PHONE, PASSWORD;
}


/*
val textInputL by lazy {
    val textInputL = BaseTextInputLayout()
    textInputL.hint = "phone"
    textInputL.boxStrokeColor = Color.TRANSPARENT
    textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
    textInputL.defineType(InputEditTextType.PHONE)
    textInputL.setInactiveUnderlineColor(Color.GREEN)
    textInputL.editText?.textColor = Color.MAGENTA
    textInputL
}

val textInputPassword by lazy {
    val textInputL = BaseTextInputLayout()
    textInputL.hint = "password"
    textInputL.boxStrokeColor = Color.MAGENTA
    textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
    textInputL.initPassword()
    textInputL
}*/
