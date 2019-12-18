package com.ageone.zenit.External.Base.Toolbar

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.Application.router
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.R
import yummypets.com.stevia.*
import kotlin.properties.Delegates

class BaseToolbar(val initModuleUI: InitModuleUI, val content: ConstraintLayout): Toolbar(currentActivity) {

    var title: String? = null
    var textColor: Int = Color.WHITE

    val firstIcon by lazy {
        val view = BaseImageView()
        view.setImageResource(R.drawable.ic_close)
        view.setPadding(3.dp, 3.dp, 3.dp, 3.dp)
        view.visibility = View.GONE
        view
    }

    val secondIcon by lazy {
        val view = BaseImageView()
        view.setImageResource(R.drawable.ic_exit)
        view.visibility = View.GONE
        view
    }

    val thirdIcon by lazy {
        val view = BaseImageView()
        view.setImageResource(R.drawable.ic_exit)
        view.visibility = View.GONE
        view
    }
    
    val textView by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = initModuleUI.textSize ?: 17F
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView
    }

    val pushIcon by lazy {
        val view = BaseView()
        view.backgroundColor = Color.RED
        view.cornerRadius = 16.dp
        view.setBackgroundColor(Color.RED)
        view.visibility = View.GONE
        view.initialize()
        view
    }

    val pushTextView by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 10F
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.visibility = View.GONE
        textView.text = ""
        textView.textColor = Color.WHITE
        textView.initialize()
        textView
    }

    fun setTitleToolbar(title: String) {
        setTitle(title)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun initialize() {

        setTitle(title)
        setTitleTextColor(textColor)

        if (initModuleUI.isBackPressed) {
            AppCompatResources.getDrawable(context, R.drawable.ic_arrow)?.let { drawable ->
                val wrappedDrawable = DrawableCompat.wrap(drawable)
                DrawableCompat.setTint(wrappedDrawable, textColor)

                navigationIcon = wrappedDrawable
                setNavigationOnClickListener {
                    router.onBackPressed()
                }
            }
        }

        initModuleUI.firstIcon?.let { icon ->
            icon.listener?.let { listener ->
                firstIcon.setOnClickListener(listener)
            }

            icon.icon?.let { icon ->
                firstIcon.visibility = View.VISIBLE
                firstIcon.setImageResource(icon)
            }
        }

        initModuleUI.text?.let { text ->
            textView.textColor = initModuleUI.textColor ?: textColor
            textView.visibility = View.VISIBLE
            textView.text = text

            initModuleUI.textListener?.let { listener ->
                textView.setOnClickListener(listener)
            }
        }

        initModuleUI.secondIcon?.let { icon ->
            icon.listener?.let { listener ->
                secondIcon.setOnClickListener(listener)
            }

            icon.icon?.let { icon ->
                secondIcon.visibility = View.VISIBLE
                secondIcon.setImageResource(icon)
            }
        }

        initModuleUI.thirdIcon?.let { icon ->
            icon.listener?.let { listener ->
                thirdIcon.setOnClickListener(listener)
            }

            icon.icon?.let { icon ->
                thirdIcon.visibility = View.VISIBLE
                thirdIcon.setImageResource(icon)
            }
        }

        renderUI()

    }

    val baseIconSize = 25

    @SuppressLint("ClickableViewAccessibility")
    private fun renderUI() {
        content.subviews(
            thirdIcon,
            secondIcon,
            textView,
            firstIcon,
            pushIcon,
            pushTextView
        )

        firstIcon
            .width(initModuleUI.firstIcon?.size ?: baseIconSize)
            .height(initModuleUI.firstIcon?.size ?: baseIconSize)
            .constrainRightToRightOf(this, 16)
            .constrainCenterYToCenterYOf(this)

        pushIcon
            .width(15)
            .height(15)
            .constrainTopToTopOf(this,8)
            .constrainRightToRightOf(this,16)

        pushTextView
            .width(15)
            .height(15)
            .constrainCenterXToCenterXOf(pushIcon)
            .constrainCenterYToCenterYOf(pushIcon)

        firstIcon.setOnTouchListener(firstIcon.onTouchListener())
        secondIcon.setOnTouchListener(secondIcon.onTouchListener())
        thirdIcon.setOnTouchListener(thirdIcon.onTouchListener())

        textView
            .constrainRightToLeftOf(firstIcon, 16)
            .constrainCenterYToCenterYOf(this)

        secondIcon
            .width(initModuleUI.secondIcon?.size ?: baseIconSize)
            .height(initModuleUI.secondIcon?.size ?: baseIconSize)
            .constrainRightToLeftOf(textView, 16)
            .constrainCenterYToCenterYOf(this)

        thirdIcon
            .width(initModuleUI.thirdIcon?.size ?: baseIconSize)
            .height(initModuleUI.thirdIcon?.size ?: baseIconSize)
            .constrainRightToLeftOf(secondIcon, 16)
            .constrainCenterYToCenterYOf(this)

    }

    private fun BaseImageView.onTouchListener(): OnTouchListener {
        return object : OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                v?.let { view ->
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            var outValue: TypedArray? = null
                            val attr:IntArray = intArrayOf(R.attr.selectableItemBackground)
                            outValue = context.obtainStyledAttributes(attr)
                            var drawable = outValue.getDrawable(0)
                            this@onTouchListener.background = drawable
                            if(Build.VERSION.SDK_INT >= 23) {
                                view.foreground = drawable
                            }
                            outValue?.recycle()
                        }

                        MotionEvent.ACTION_UP -> {
                        }

                        MotionEvent.ACTION_MOVE -> {
                        }

                        else -> {

                        }
                    }
                }

                return false
            }

        }
    }

    var countPush: Int by Delegates.observable(0) {property, oldValue, newValue ->
        if (oldValue == newValue) return@observable


        if( newValue != oldValue ){
                if(newValue == 0){
                    pushIcon.visibility = View.GONE
                    pushTextView.visibility = View.GONE
                } else {
                    pushIcon.visibility = View.VISIBLE
                    pushTextView.visibility = View.VISIBLE
                    pushTextView.text = newValue.toString()
                }
            }
        }
    }


