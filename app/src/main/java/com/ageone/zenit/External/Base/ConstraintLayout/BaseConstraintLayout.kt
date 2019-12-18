package com.ageone.zenit.External.Base.ConstraintLayout

import android.graphics.Outline
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.Button.BaseButton
import com.ageone.zenit.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import timber.log.Timber
import yummypets.com.stevia.constrainBottomToBottomOf

open class BaseConstraintLayout: ConstraintLayout(currentActivity) {
    var gradientDrawable = GradientDrawable()

    var cornerRadius: Int? = null
    var backgroundColor: Int? = null

    var gradient: Int? = null
    var orientation: GradientDrawable.Orientation? = null

    var borderColor: Int? = null
    var borderWidth: Int? = null

    fun initialize() {

        gradientDrawable.shape = GradientDrawable.RECTANGLE

        cornerRadius?.let { cornerRadius ->
            setOnlyTopRoundedCorners(cornerRadius.toFloat())
        }

        borderWidth?.let { borderWidth ->
            borderColor?.let { borderColor ->
                gradientDrawable.setStroke(borderWidth, borderColor)
            }
        }

        backgroundColor?.let { backgroundColor ->
            gradientDrawable.setColor(backgroundColor)
            gradient?.let { gradient ->
                gradientDrawable.colors = intArrayOf(backgroundColor, gradient)
            }
        }

        orientation?.let { orientation ->
            gradientDrawable.orientation = orientation
        }

        background = gradientDrawable
    }
}


fun View.setOnlyTopRoundedCorners(radius: Float) {

    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(0, 0, width, (height + radius).toInt(), radius)
        }
    }
    clipToOutline = true

}

fun BaseConstraintLayout.setButtonAboveKeyboard(button: BaseButton) {
    val rectLayout = Rect()
    getWindowVisibleDisplayFrame(rectLayout)

    val usableRect = rectLayout.bottom - rectLayout.top

    viewTreeObserver.addOnGlobalLayoutListener {

        val statusBarInDp = (utils.variable.statusBarHeight.toFloat() / rootView.height.toFloat()) * utils.variable.displayHeightDp.toFloat()

        val layoutHeight = if (statusBarInDp > 24) {
            usableRect
        } else {
            usableRect - utils.variable.statusBarHeight
        }

        val rect = Rect()
        getWindowVisibleDisplayFrame(rect)

        val displayHeight = rootView.height
        val usableHeight = rect.bottom - rect.top
        val bottomButton = displayHeight - layoutHeight

        val keyboardHeight = displayHeight - usableHeight - bottomButton
        val coff: Float = keyboardHeight.toFloat() / layoutHeight
        val heightInDp = utils.variable.displayHeightDp

        val margin = if (statusBarInDp > 24) {
            (heightInDp * coff)
        } else {
            (heightInDp * coff) - 8
        }


        if (margin > 50) {
            button.constrainBottomToBottomOf(this, margin.toInt())
        } else if (margin < 50) {
            button.constrainBottomToBottomOf(this)
        }
    }
}

fun BaseConstraintLayout.dismissFocus(view: EditText?) {

    viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        val height: Float = rootView.height.toFloat()

        getWindowVisibleDisplayFrame(rect)

        val heightDiff =
            ((((height - rect.height()) / height) * utils.variable.displayHeightDp).toInt())

        if (heightDiff < 100) {
            view?.clearFocus()
            view?.isFocusableInTouchMode = true
        }
    }
}

fun BaseConstraintLayout.setScrollableView(view: BaseTextInputLayout?, recyclerView: BaseRecyclerView) {
    val rectLayout = Rect()
    getWindowVisibleDisplayFrame(rectLayout)

    val usableRect = rectLayout.bottom - rectLayout.top
    var isFocus = false


    viewTreeObserver.addOnGlobalLayoutListener {

        val statusBarInDp = (utils.variable.statusBarHeight.toFloat() / rootView.height.toFloat()) * utils.variable.displayHeightDp.toFloat()
        val layoutHeight = usableRect - if (statusBarInDp > 24) 0 else utils.variable.statusBarHeight

        val rect = Rect()
        getWindowVisibleDisplayFrame(rect)

        val displayHeight = rootView.height
        val usableHeight = rect.bottom - rect.top
        val bottomButton = displayHeight - layoutHeight
        val keyboardHeight = displayHeight - usableHeight - bottomButton
        val coff: Float = keyboardHeight.toFloat() / layoutHeight

        val heightInDp = utils.variable.displayHeightDp

        val margin = heightInDp * coff - if (statusBarInDp > 24) 0 else 8

        if (margin > 50) {
            Timber.i("View up : $margin")
            recyclerView.constrainBottomToBottomOf(this, margin.toInt())
            isFocus = true

        } else if (margin.toInt() < 50) {

            recyclerView.constrainBottomToBottomOf(this)
            if (isFocus) {
                view?.editText?.clearFocus()
                isFocus = false
            }
        }

    }

}

