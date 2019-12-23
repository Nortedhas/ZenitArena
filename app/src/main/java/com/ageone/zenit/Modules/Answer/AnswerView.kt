package com.ageone.zenit.Modules.Answer

import android.graphics.Color
import android.text.InputType
import androidx.core.widget.doOnTextChanged
import com.ageone.zenit.Application.router
import com.ageone.zenit.Application.rxData
import com.ageone.zenit.External.Base.Button.BaseButton
import com.ageone.zenit.External.Base.EditText.limitLength
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.InitModuleUI
import timber.log.Timber
import yummypets.com.stevia.*

class AnswerView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = AnswerViewModel()

    val textInputAnswer by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#CBCBCB")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 20F
            editText.limitLength(800)
            editText.maxLines = 15
        }

        textInput
    }

    val textViewSymbolCount by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#D7D7D8")
        textView.textSize = 12F
        textView.text = "Не более 800 знаков"
        textView.initialize()
        textView
    }

    val buttonAnswer by lazy {
        val button = BaseButton()
        button.backgroundColor = Color.parseColor("#00ACEB")
        button.textColor = Color.WHITE
        button.textSize = 20F
        button.cornerRadius = 6.dp
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.text = "Готово"
        button.initialize()
        button
    }


    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Мой ответ"
        toolbar.textColor = Color.parseColor("#00ACEB")

        renderToolbar()

        renderUIO()
        bindUI()

        if(rxData.answer.isNotBlank() && rxData.callAnswer == "quiz") {
            textInputAnswer.editText?.setText(rxData.answer)
        }

        if(rxData.answerPosition.isNotBlank() && rxData.callAnswer == "answerPosition") {
            textInputAnswer.editText?.setText(rxData.answerPosition)
        }

        if(rxData.answerOffer.isNotBlank() && rxData.callAnswer == "answerOffer") {
            textInputAnswer.editText?.setText(rxData.answerOffer)
        }

        textInputAnswer.editText?.doOnTextChanged { text, start, count, after ->
            when(rxData.callAnswer) {
                "quiz" -> {
                    viewModel.model.answer = text.toString()
                }
                "answerPosition" -> {
                    viewModel.model.answerPosition = text.toString()
                }
                "answerOffer" -> {
                    viewModel.model.answerOffer = text.toString()
                }
            }
        }

        buttonAnswer.setOnClickListener {
            router.onBackPressed()
        }
        onDeInit = {
            rxData.answer = viewModel.model.answer
            rxData.answerPosition = viewModel.model.answerPosition
            rxData.answerOffer = viewModel.model.answerOffer
            unBind()
        }

    }

    fun bindUI() {
        /*compositeDisposable.addAll(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

}

fun AnswerView.renderUIO() {
    innerContent.subviews(
        textInputAnswer,
        textViewSymbolCount,
        buttonAnswer
    )

    textInputAnswer
        .constrainTopToTopOf(innerContent)
        .fillHorizontally(16)

    textViewSymbolCount
        .constrainTopToBottomOf(textInputAnswer,26)
        .constrainRightToRightOf(innerContent,16)

    buttonAnswer
        .constrainBottomToBottomOf(innerContent,40)
        .fillHorizontally(16)
        .height(53)

}


