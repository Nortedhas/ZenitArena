package com.ageone.zenit.Modules.Auth

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.Libraries.Alert.alertManager
import com.ageone.zenit.External.Libraries.Alert.single
import com.ageone.zenit.Models.User.user
import com.ageone.zenit.Modules.Auth.rows.AuthForgotPasswordViewHolder
import com.ageone.zenit.Modules.Auth.rows.initialize
import com.ageone.zenit.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.TextInputViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import com.ageone.zenit.R
import com.ageone.zenit.UIComponents.ViewHolders.TitleWithLogoViewHolder
import yummypets.com.stevia.*

class AuthView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = AuthViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val imageViewBackground by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }



    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = ""

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        imageViewBackground.setBackgroundResource(R.drawable.back_lion)
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

        private val TitleWithLogoType = 3
        private val TextInputType = 0
        private val ButtonType = 1
        private val PasswordTextType = 2

        override fun getItemCount() = 6//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> TitleWithLogoType
            1,2 -> TextInputType
            3,5 -> ButtonType
            4 -> PasswordTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                TitleWithLogoType -> {
                    TitleWithLogoViewHolder(layout)
                }
                TextInputType -> {
                    TextInputViewHolder(
                        layout
                    )
                }
                ButtonType -> {
                    ButtonViewHolder(
                        layout
                    )
                }
                PasswordTextType -> {
                    AuthForgotPasswordViewHolder(
                        layout
                    )
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is TitleWithLogoViewHolder -> {
                    holder.initialize("ДОБРО ПОЖАЛОВАТЬ!")
                }

                is TextInputViewHolder -> {
                    when(position) {
                        1 -> {
                            holder.initialize("Email", InputEditTextType.EMAIL)
                        }
                        2 -> {
                            holder.initialize("Пароль", InputEditTextType.PASSWORD)
                            holder.textInput.constrainTopToTopOf(holder.constraintLayout,25)

                        }
                    }
                }

                is ButtonViewHolder -> {
                    when(position) {
                        3 -> {
                            holder.initialize("Войти")
                            holder.button.setOnClickListener {
                                user.isAuthorized = true //todo: delete after add server
                                emitEvent?.invoke(AuthViewModel.EventType.OnEnterPressed.name)
                            }
                        }
                        5 -> {
                            holder.initialize("Зарегистрироваться")
                            holder.button.setOnClickListener {
                                emitEvent?.invoke(AuthViewModel.EventType.OnRegistrationPressed.name)
                            }
                        }
                    }
                }

                is AuthForgotPasswordViewHolder -> {
                    holder.initialize()
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
            }
        }
    }
}


fun AuthView.renderUIO() {
    backgroundFullscreen.subviews(
        imageViewBackground
    )

    imageViewBackground
        .width(utils.tools.getActualSizeFromDes(505))
        .height(utils.tools.getActualSizeFromDes(400))
        .constrainBottomToBottomOf(backgroundFullscreen, (utils.variable.displayHeightDp - utils.tools.getActualSizeFromDes(400)).toInt() + 70)
        .constrainLeftToLeftOf(backgroundFullscreen, utils.tools.getActualSizeFromDes(40).toInt())

    renderBodyTable()
}


