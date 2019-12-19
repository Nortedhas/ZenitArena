package com.ageone.zenit.External.Libraries.Alert

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.coordinator
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

val alertManager
    get() = AlertManager()

//TODO: replace in base

class AlertManager {

    fun renderUI() {

        constraintLayout.subviews(
            imageViewIcon,
            textViewTitle,
            textViewMessage
        )

        imageViewIcon
            .constrainTopToTopOf(constraintLayout, 8)
            .constrainLeftToLeftOf(constraintLayout)
            .constrainRightToRightOf(constraintLayout)
            .height(100)
            .width(100)

        textViewTitle
            .fillHorizontally()
            .constrainTopToBottomOf(imageViewIcon, 8)
            .constrainLeftToLeftOf(constraintLayout, 16)
            .constrainRightToRightOf(constraintLayout, 16)

        textViewMessage
            .fillHorizontally()
            .constrainLeftToLeftOf(constraintLayout, 16)
            .constrainTopToBottomOf(textViewTitle, 8)
            .constrainRightToRightOf(constraintLayout, 16)

    }

    // MARK: UI

    val constraintLayout: ConstraintLayout by lazy {
        val constraintLayout = ConstraintLayout(currentActivity)
        constraintLayout
    }

    val imageViewIcon: ImageView by lazy {
        val imageViewIcon = ImageView(currentActivity)
        imageViewIcon
    }

    // MARK: viewHolder

    val textViewTitle: BaseTextView by lazy {
        val textViewTitle = BaseTextView()
        textViewTitle.gravity = View.TEXT_ALIGNMENT_VIEW_END
        textViewTitle.typeface = Typeface.DEFAULT_BOLD
        textViewTitle.textSize = 16F
        textViewTitle.textColor = Color.BLACK
        textViewTitle.setBackgroundColor(Color.TRANSPARENT)
        textViewTitle

    }

    // MARK: textViewMessage

    val textViewMessage: BaseTextView by lazy {
        val textViewMessage = BaseTextView()
        textViewMessage.gravity = View.TEXT_ALIGNMENT_VIEW_END
        textViewMessage.typeface = Typeface.DEFAULT
        textViewMessage.textSize = 15F
        textViewMessage.textColor = Color.DKGRAY
        textViewMessage.setBackgroundColor(Color.TRANSPARENT)
        textViewMessage
    }

}

// MARK: Single

fun AlertManager.single(title: String, message: String, image: Int? = null, blockingExternalPresses:Boolean? = null,
                        button: String = "OK", completion: ((DialogInterface, Int) -> (Unit))? = null) {
    renderUI()
    if (completion == null) {
        textViewMessage
            .constrainBottomToBottomOf(constraintLayout,16)
    }
    if (image == null) {
        imageViewIcon
            .height(0)
            .width(0)
            .constrainTopToTopOf(constraintLayout, 0)
    } else {
        imageViewIcon.setImageResource(image)
    }
    textViewTitle.text = title
    textViewMessage.text = message
    val builder = AlertDialog.Builder(currentActivity)

    if (blockingExternalPresses != null){
        if(blockingExternalPresses){
            builder.setCancelable(false)
        }else{
            builder.setCancelable(true)
        }

    }
    builder.setView(constraintLayout)
    completion?.let{
        builder.setPositiveButton(button, completion)
    }
    builder.show()

}

// MARK: Double

fun AlertManager.double(
    title: String, message: String, image: Int? = null,blockingExternalPresses:Boolean? = null,
    button: String = "OK", completion: (DialogInterface, Int) -> (Unit),
    buttonCancel: String = "CANCEL", completionCancel: ((DialogInterface, Int) -> (Unit))? = null
) {

    renderUI()

    if (image == null) {
        imageViewIcon
            .height(0)
            .width(0)
            .constrainTopToTopOf(constraintLayout, 0)
    } else {
        imageViewIcon.setImageResource(image)
    }

    textViewTitle.text = title
    textViewMessage.text = message

    val builder = AlertDialog.Builder(currentActivity)
    if (blockingExternalPresses != null){
        if(blockingExternalPresses){
            builder.setCancelable(false)
        }else{
            builder.setCancelable(true)
        }

    }

    builder.setView(constraintLayout)
    builder.setCancelable(false)
    builder.setPositiveButton(button, completion)
    completionCancel?.let {
        builder.setNegativeButton(buttonCancel, completionCancel)
    }
    builder.show()
}

// MARK: With list

fun AlertManager.list(title: String,variants: Array<String>,blockingExternalPresses:Boolean? = null,completion: (DialogInterface, Int) -> (Unit)) {

    // setup the alert builder
    val builder = AlertDialog.Builder(currentActivity)
    builder.setTitle(title)

    if (blockingExternalPresses != null){
        if(blockingExternalPresses){
            builder.setCancelable(false)
        }else{
            builder.setCancelable(true)
        }

    }
    // add a list
    builder.setItems(variants, completion)
    builder.setCancelable(false)

    // create and show the alert dialog
    val dialog = builder.create()
    dialog.show()

}

// MARK: block ui

fun AlertManager.blockUI(isVisible: Boolean) {

    if (isVisible) {
        coordinator.blockConstraint.visibility = View.VISIBLE
        coordinator.circularProgress.visibility = View.VISIBLE
        coordinator.blockConstraint.setOnClickListener {  }
    } else {
        coordinator.blockConstraint.visibility = View.GONE
        coordinator.circularProgress.visibility = View.GONE
    }

}