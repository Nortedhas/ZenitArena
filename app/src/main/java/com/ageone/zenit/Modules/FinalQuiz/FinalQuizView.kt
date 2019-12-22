package com.ageone.zenit.Modules.FinalQuiz

import android.graphics.Color
import android.os.Handler
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.ageone.zenit.External.Extensions.Activity.hideKeyboard
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.FinalQuiz.rows.FinalQuizTextInputViewHolder
import com.ageone.zenit.Modules.FinalQuiz.rows.FinalQuizTextViewHolder
import com.ageone.zenit.Modules.FinalQuiz.rows.initialize
import com.ageone.zenit.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class FinalQuizView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = FinalQuizViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val textQuiz = mutableMapOf<Int, String>()

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        textQuiz[0] = "Уточните, пожалуйста, позицию в рамках функции"
        textQuiz[2] = "Ваши предложения/пожелания/ замечания по организации деятельности Волонтерского центра ФК «Зенит»"

        toolbar.title = "Обратная связь"
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
        /*compositeDisposable.addAll(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val FinalQuizTextType = 0
        private val FinalQuizTextInputType = 1
        private val FinalQuizButtonType = 2

        override fun getItemCount() = 5//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0,2 -> FinalQuizTextType
            1,3 -> FinalQuizTextInputType
            else -> FinalQuizButtonType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                FinalQuizTextType -> {
                    FinalQuizTextViewHolder(layout)
                }
                FinalQuizTextInputType -> {
                    FinalQuizTextInputViewHolder(layout)
                }
                FinalQuizButtonType -> {
                    ButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is FinalQuizTextViewHolder -> {
                    holder.initialize(textQuiz[position] ?: "")
                }
                is FinalQuizTextInputViewHolder -> {
                    holder.initialize("Мой ответ", InputEditTextType.TEXT)
                    holder.textInputFinalQuiz.editText?.setOnTouchListener { v, event ->
                        if(event.action == MotionEvent.ACTION_DOWN) {
                            Handler().postDelayed({
                                currentActivity?.hideKeyboard()
                            },300)
                            emitEvent?.invoke(FinalQuizViewModel.EventType.OnAnswerPressed.name)
                        }
                        false
                    }
                }
                is ButtonViewHolder -> {
                    holder.initialize("Отправить")
                }
            }
        }
    }
}

fun FinalQuizView.renderUIO() {

    renderBodyTable()

}


