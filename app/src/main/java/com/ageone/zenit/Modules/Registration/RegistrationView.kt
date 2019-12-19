package com.ageone.zenit.Modules.Registration

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.Application.intent
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ConstraintLayout.dismissFocus
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.ageone.zenit.External.Extensions.Activity.hideKeyboard
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.External.Libraries.Alert.alertManager
import com.ageone.zenit.External.Libraries.Alert.list
import com.ageone.zenit.Modules.Registration.rows.*
import com.ageone.zenit.R
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*

class RegistrationView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = RegistrationViewModel()

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

        private val TitleType = 0
        private val TextInputType = 1
        private val ActionTextType = 3
        private val PlaceTextInputType = 4
        private val PhotoType = 5

        override fun getItemCount() = 17//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> TitleType
            in 1 .. 12  -> TextInputType
            13 -> ActionTextType
            14,15 -> PlaceTextInputType
            16 -> PhotoType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                TitleType -> {
                    RegistrationTitleViewHolder(layout)
                }
                TextInputType -> {
                    RegistrationTextInputViewHolder(layout)
                }
                ActionTextType -> {
                    RegistrationActionTextViewHolder(layout)
                }
                PlaceTextInputType -> {
                    RegistrationPlaceTextInputViewHolder(layout)
                }
                PhotoType -> {
                    RegistrationPhotoViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is RegistrationTitleViewHolder -> {
                    holder.initialize()
                }
                is RegistrationTextInputViewHolder -> {
                    when(position) {
                        1 -> {
                            holder.initialize("Фамилия", InputEditTextType.TEXT)
                        }
                        2 -> {
                            holder.initialize("Имя", InputEditTextType.TEXT)
                        }
                        3 -> {
                            holder.initialize("Отчество", InputEditTextType.TEXT)
                        }
                        4 -> {
                            holder.initialize("Ваш пол", InputEditTextType.TEXT)
                            innerContent.dismissFocus(holder.textInputRegistration.editText)

                            holder.textInputRegistration.editText?.setOnTouchListener { v, event ->
                                if(event.action == MotionEvent.ACTION_DOWN) {
                                    Handler().postDelayed({
                                        currentActivity?.hideKeyboard()
                                    },300)

                                    val genderVariant = arrayOf("Женский", "Мужской")
                                    alertManager.list("Ваш пол", genderVariant) { _, index ->
                                    holder.textInputRegistration.editText?.setText(genderVariant[index])}
                                }
                                false
                            }
                        }
                        5 -> {
                            holder.initialize("Дата рождения", InputEditTextType.TEXT)
                            innerContent.dismissFocus(holder.textInputRegistration.editText)

                            holder.textInputRegistration.editText?.setOnTouchListener{ v, event->
                                if(event.action == MotionEvent.ACTION_DOWN) {
                                    Handler().postDelayed({
                                        currentActivity?.hideKeyboard()
                                    },300)
                                    startDatePicker(holder.textInputRegistration)
                                }
                                false
                            }
                        }
                        6 -> {
                            holder.initialize("Место рождения", InputEditTextType.TEXT)
                        }
                        7 -> {
                            holder.initialize("Email", InputEditTextType.EMAIL)
                        }
                        8 -> {
                            holder.initialize("Номер телефона", InputEditTextType.PHONE)
                        }
                        9 -> {
                            holder.initialize("Серия и номер паспорта", InputEditTextType.NUMERIC)
                        }
                        10 -> {
                            holder.initialize("Кем и когда выдан", InputEditTextType.TEXT)
                            holder.textInputRegistration.editText?.maxLines = 3
                        }
                        11 -> {
                            holder.initialize("СНИЛС", InputEditTextType.NUMERIC)
                        }
                        12 -> {
                            holder.initialize("ИНН", InputEditTextType.NUMERIC)
                        }
                    }
                }
                is RegistrationActionTextViewHolder -> {
                    holder.initialize()
                    holder.textViewAction.setOnClickListener {
                        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JDSPAPOUU0U"))
                        currentActivity?.startActivity(intent)                    }
                }
                is RegistrationPlaceTextInputViewHolder -> {
                    when(position) {
                        14 -> {
                            holder.initialize("Место работы/учёбы", InputEditTextType.TEXT)
                        }
                        15 -> {
                            holder.initialize("Размер куртки", InputEditTextType.TEXT)
                            innerContent.dismissFocus(holder.textInputPlace.editText)

                            holder.textInputPlace.editText?.setOnTouchListener { v, event ->
                                if(event.action == MotionEvent.ACTION_DOWN) {

                                    Handler().postDelayed({
                                        currentActivity?.hideKeyboard()
                                    },300)

                                    val sizeVariants = arrayOf("S", "M", "L", "XL", "XXL")
                                    alertManager.list("Размер куртки", sizeVariants) { _, index ->
                                        holder.textInputPlace.editText?.setText(sizeVariants[index])}
                                }
                                false
                            }
                        }
                    }
                }
                is RegistrationPhotoViewHolder -> {
                    holder.initialize()
                    holder.textViewConvention.setOnClickListener {
                        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JDSPAPOUU0U"))
                        currentActivity?.startActivity(intent)
                    }
                }
            }
        }
    }
}

fun RegistrationView.renderUIO() {
    backgroundFullscreen.subviews(
        imageViewBackground,
        imageViewLogo
    )

    imageViewBackground
        .width(utils.tools.getActualSizeFromDes(505))
        .height(utils.tools.getActualSizeFromDes(400))
        .constrainTopToTopOf(backgroundFullscreen)
        .constrainLeftToLeftOf(backgroundFullscreen, utils.tools.getActualSizeFromDes(40).toInt())

    imageViewLogo
        .width(utils.tools.getActualSizeFromDes(208))
        .height(utils.tools.getActualSizeFromDes(78))
        .constrainTopToTopOf(backgroundFullscreen, 76)
        .constrainLeftToLeftOf(backgroundFullscreen)
        .constrainRightToRightOf(backgroundFullscreen)

    renderBodyTable()
}

fun RegistrationView.startDatePicker(textInput: BaseTextInputLayout) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(currentActivity, R.style.TimePickerTheme,DatePickerDialog.OnDateSetListener { view, year, month, day ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")

        textInput.editText?.setText(simpleDateFormat.format(calendar.time))
    }, year, month,day)

    datePickerDialog.show()
}




