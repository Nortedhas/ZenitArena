package com.ageone.zenit.Modules.Auth

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.Libraries.Alert.alertManager
import com.ageone.zenit.External.Libraries.Alert.single
import com.ageone.zenit.Models.User.user
import com.ageone.zenit.Modules.Auth.rows.AuthButtonViewHolder
import com.ageone.zenit.Modules.Auth.rows.AuthTextInputViewHolder
import com.ageone.zenit.Modules.Auth.rows.initialize
import com.ageone.zenit.R
import yummypets.com.stevia.*

class AuthView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = AuthViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val imageViewLogo by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    val imageViewBackground by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    val textViewWelcome by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#00ACEB")
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.text = "ДОБРО ПОЖАЛОВАТЬ!"
        textView
    }



    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = ""

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        imageViewBackground.setBackgroundResource(R.drawable.back_lion)
        imageViewLogo.setBackgroundResource(R.drawable.zenit_logo)
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

        private val TextInputType = 0
        private val ButtonType = 1
        private val PasswordTextType = 2

        override fun getItemCount() = 4//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0,1 -> TextInputType
            2,3 -> ButtonType
            else -> PasswordTextType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                TextInputType -> {
                    AuthTextInputViewHolder(layout)
                }
                ButtonType -> {
                    AuthButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is AuthTextInputViewHolder -> {
                    when(position) {
                        0-> {
                            holder.initialize("Email", InputEditTextType.EMAIL)
                        }
                        1 -> {
                            holder.initialize("Пароль", InputEditTextType.PASSWORD)
                            holder.textInputAuth.constrainTopToTopOf(holder.constraintLayout,25)

                        }
                    }

                }
                is AuthButtonViewHolder -> {
                    when(position) {
                        2 -> {
                            holder.initialize("Войти")
                            holder.buttonAuth.setOnClickListener {
                                user.isAuthorized = true //todo: delete after add server
                                emitEvent?.invoke(AuthViewModel.EventType.OnEnterPressed.name)
                            }

                            holder.textViewPassword.setOnClickListener {
                                alertManager.single(
                                    "На вашу почту отправлен новый пароль",
                                    "",
                                    R.drawable.ic_lock,
                                    true,
                                    "Ok"
                                ) { _, index -> }
                            }
                        }
                        3 -> {
                            holder.buttonAuth.constrainTopToTopOf(holder.constraintLayout, 33)
                            holder.initialize("Зарегистрироваться")
                            holder.buttonAuth.setOnClickListener {
                                emitEvent?.invoke(AuthViewModel.EventType.OnRegistrationPressed.name)
                            }
                            holder.textViewPassword.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}


fun AuthView.renderUIO() {
    backgroundFullscreen.subviews(
        imageViewBackground,
        imageViewLogo,
        textViewWelcome
    )

    imageViewBackground
        .width(utils.tools.getActualSizeFromDes(505))
        .height(utils.tools.getActualSizeFromDes(400))
        .constrainBottomToBottomOf(backgroundFullscreen, (utils.variable.displayHeightDp - utils.tools.getActualSizeFromDes(400)).toInt() + 70)
        .constrainLeftToLeftOf(backgroundFullscreen, utils.tools.getActualSizeFromDes(40).toInt())

    imageViewLogo
        .width(utils.tools.getActualSizeFromDes(208))
        .height(utils.tools.getActualSizeFromDes(78))
        .constrainTopToTopOf(backgroundFullscreen, 76)
        .constrainLeftToLeftOf(backgroundFullscreen)
        .constrainRightToRightOf(backgroundFullscreen)

    textViewWelcome
        .constrainTopToBottomOf(imageViewLogo,50)
        .constrainCenterXToCenterXOf(backgroundFullscreen)

    renderBodyTable()
}


