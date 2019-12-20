package com.ageone.zenit.Modules.Quiz

import android.graphics.Color
import android.os.Handler
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.Application.rxData
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.ageone.zenit.External.Extensions.Activity.hideKeyboard
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.Modules.Quiz.rows.QuizButtonViewHolder
import com.ageone.zenit.Modules.Quiz.rows.QuizTextInputViewHolder
import com.ageone.zenit.Modules.Quiz.rows.QuizTextViewHolder
import com.ageone.zenit.Modules.Quiz.rows.initialize
import com.ageone.zenit.R
import yummypets.com.stevia.*

class QuizView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = QuizViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Опросы"
        toolbar.textColor = Color.parseColor("#00ACEB")

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


        renderUIO()
        bindUI()

        onDeInit = {
            unBind()
        }

    }

    fun bindUI() {
        compositeDisposable.addAll(
            RxBus.listen(RxEvent.EventChangeAnswer::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val QuizTextType = 0
        private val QuizInputType = 1
        private val QuizButtonType = 2

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> QuizTextType
            1 -> QuizInputType
            2 -> QuizButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                QuizTextType -> {
                    QuizTextViewHolder(layout)
                }
                QuizInputType -> {
                    QuizTextInputViewHolder(layout)
                }
                QuizButtonType -> {
                    QuizButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is QuizTextViewHolder -> {
                    holder.initialize(
                        R.drawable.pic_quiz_1,
                        "В какие компьютерные игры вы играете?",
                        "Футбольный клубы встречались на поле 19 раз, из которых ФК «Зенит» одержал победу 10 раз, а ПФК «Томь» всего 3. " +
                                "В ничью закончились 6 встреч. Последнюю из трех своих побед томский клуб одержал 10 лет назад со счетом 2:0. " +
                                "В свою очередь сине-бело-голубые не сдают позиции и всего два раза отпускали томичей с ничейным счетом за прошедшие 10 лет.")
                }
                is QuizTextInputViewHolder -> {
                    holder.initialize("Мой ответ", InputEditTextType.TEXT)
                    if(rxData.answer.isNotBlank()) {
                        holder.textInputQuiz?.editText?.setText(rxData.answer)
                    }
                    holder.textInputQuiz.editText?.setOnTouchListener{ v, event ->
                        if(event.action == MotionEvent.ACTION_DOWN) {
                            Handler().postDelayed({
                                currentActivity?.hideKeyboard()
                            },300)

                            rootModule.emitEvent?.invoke(QuizViewModel.EventType.OnAnswerPressed.name)
                        }
                        false
                    }
                }
                is QuizButtonViewHolder -> {
                    holder.initialize("Далее")
                }
            }
        }
    }
}

fun QuizView.renderUIO() {

    renderBodyTable()
}


