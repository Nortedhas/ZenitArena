package com.ageone.zenit.Modules.Registration

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Handler
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.zenit.Application.REQUEST_GET_PHOTO
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
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.Modules.Registration.rows.*
import com.ageone.zenit.R
import com.ageone.zenit.SCAG.Image
import com.ageone.zenit.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.TitleWithLogoViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class RegistrationView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = RegistrationViewModel()

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

        toolbar.height(0)
        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        bodyTable.setItemViewCacheSize(18)

        imageViewBackground.setBackgroundResource(R.drawable.back_lion)
        renderUIO()
        bindUI()

        onDeInit = {
            unBind()
        }

    }

    var currentLoadImage: Image? = null

    fun bindUI() {
        compositeDisposable.addAll(
            RxBus.listen(RxEvent.EventLoadImage::class.java).subscribe { event ->
                currentLoadImage = event.loadedImage
                bodyTable.adapter?.notifyItemChanged(viewAdapter.itemCount - 2)
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val TitleWithLogoType = 0
        private val TextInputType = 1
        private val ActionTextType = 3
        private val PhotoType = 4
        private val ButtonType = 5
        private val ConventionType = 6

        override fun getItemCount() = 19//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> TitleWithLogoType
            in 1..12 -> TextInputType
            13 -> ActionTextType
            14,15 -> TextInputType
            16 -> PhotoType
            17 -> ButtonType
            18 -> ConventionType
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
                    RegistrationTextInputViewHolder(layout)
                }
                ActionTextType -> {
                    RegistrationActionTextViewHolder(layout)
                }
                PhotoType -> {
                    RegistrationPhotoViewHolder(layout)
                }
                ButtonType -> {
                    ButtonViewHolder(layout)
                }
                ConventionType -> {
                    RegistrationConventionViewHolder(layout)
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
                    holder.initialize("РЕГИСТРАЦИЯ")
                }
                is RegistrationTextInputViewHolder -> {
                    when(position) {
                        1 -> {
                            holder.initialize("Фамилия", InputEditTextType.TEXT)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.secondName = text.toString()
                            }
                        }
                        2 -> {
                            holder.initialize("Имя", InputEditTextType.TEXT)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.firstName = text.toString()
                            }
                        }
                        3 -> {
                            holder.initialize("Отчество", InputEditTextType.TEXT)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.lastName = text.toString()
                            }
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
                                        holder.textInputRegistration.editText?.setText(genderVariant[index])
                                    }
                                }
                                false
                            }

                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.gender = text.toString()
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
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                val date= SimpleDateFormat("dd.MM.yyyy").parse(text.toString())
                                viewModel.model.birthDate = TimeUnit.MILLISECONDS.toSeconds(date.time).toInt()
                            }
                        }
                        6 -> {
                            holder.initialize("Место рождения", InputEditTextType.TEXT)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.birthPlace = text.toString()
                            }
                        }
                        7 -> {
                            holder.initialize("Email", InputEditTextType.EMAIL)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.email = text.toString()
                            }
                        }
                        8 -> {
                            holder.initialize("Номер телефона", InputEditTextType.PHONE)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.phone = text.toString()
                            }
                        }
                        9 -> {
                            holder.initialize("Серия и номер паспорта", InputEditTextType.NUMERIC)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.passportNum = text.toString().toInt()
                            }
                        }
                        10 -> {
                            holder.initialize("Кем и когда выдан", InputEditTextType.TEXT)
                            holder.textInputRegistration.editText?.maxLines = 3
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.passportIssue = text.toString()
                            }
                        }
                        11 -> {
                            holder.initialize("СНИЛС", InputEditTextType.NUMERIC)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.snilsNum = text.toString().toInt()
                            }
                        }
                        12 -> {
                            holder.initialize("ИНН", InputEditTextType.NUMERIC)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.innNum = text.toString().toInt()
                            }
                        }
                        14 -> {
                            holder.initialize("Место работы/учёбы", InputEditTextType.TEXT)
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.careerPlace = text.toString()
                            }
                        }
                        15 -> {
                            holder.initialize("Размер куртки", InputEditTextType.TEXT)
                            innerContent.dismissFocus(holder.textInputRegistration.editText)

                            holder.textInputRegistration.editText?.setOnTouchListener { v, event ->
                                if(event.action == MotionEvent.ACTION_DOWN) {

                                    Handler().postDelayed({
                                        currentActivity?.hideKeyboard()
                                    },300)

                                    val sizeVariants = arrayOf("S", "M", "L", "XL", "XXL")
                                    alertManager.list("Размер куртки", sizeVariants) { _, index ->
                                        holder.textInputRegistration.editText?.setText(sizeVariants[index])}
                                }
                                false
                            }
                            holder.textInputRegistration.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.jacketSize = text.toString()
                            }
                        }
                    }
                }

                is RegistrationActionTextViewHolder -> {
                    holder.initialize()
                    holder.textViewAction.setOnClickListener {
                        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JDSPAPOUU0U"))
                        currentActivity?.startActivity(intent)}
                }

                is RegistrationPhotoViewHolder -> {
                    holder.initialize(currentLoadImage?.preview)
                    holder.imageViewPhoto.setOnClickListener {
                        intent = Intent(Intent.ACTION_PICK)
                        // intent.addCategory(Intent.CATEGORY_OPENABLE)
                        intent.type = "image/*"
                        currentActivity?.startActivityForResult(
                            Intent.createChooser(intent, "Select Picture"), REQUEST_GET_PHOTO)
                    }
                }

                is ButtonViewHolder -> {
                    holder.initialize("Зарегистрироваться")
                    holder.button.setOnClickListener {
                        viewModel.validate {}
                    }
                }

                is RegistrationConventionViewHolder -> {
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
        imageViewBackground
    )

    imageViewBackground
        .width(utils.tools.getActualSizeFromDes(505))
        .height(utils.tools.getActualSizeFromDes(400))
        .constrainBottomToBottomOf(backgroundFullscreen, (utils.variable.displayHeightDp - utils.tools.getActualSizeFromDes(400)).toInt() + 70)
        .constrainLeftToLeftOf(backgroundFullscreen, utils.tools.getActualSizeFromDes(40).toInt())

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




