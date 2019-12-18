package com.ageone.zenit.External.Base.BottomSheet

import android.graphics.Color
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.animation.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.ageone.zenit.Application.coordinator
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ConstraintLayout.BaseConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.zenit.External.Base.View.BaseView
import timber.log.Timber

import yummypets.com.stevia.*

class BaseBottomSheet(val parent: ConstraintLayout): BaseConstraintLayout() {
//    val parent = router.layout

    var heightInPercent: Int = 50

    val viewLabel by lazy {
        val view = BaseView()
        view.backgroundColor = Color.GRAY
        view.cornerRadius = 16.dp
        view.initialize()
        view
    }

    val viewLabelBackground by lazy {
        val view = BaseView()
        view.backgroundColor = this.backgroundColor
        view.initialize()
        view
    }

    init {
        setOnTouchListener(onBottomSheetTouchListener)
    }

    //position finger in view
    private var touchPosition = 0.0F

    private var isShow = false

    private var maxHeight: Float = (utils.variable.displayHeightPx - utils.variable.navigationBarHeight) * (1 - heightInPercent / 100.0F)

    private var animationRecycler = AlphaAnimation(0.0F, 1.0F)

    private fun slideView(margin: Float, duration: Long) {
        val constraintSet = ConstraintSet().apply {
            //clone current layout
            clone(parent)
            //unlink view in innerContent
            clear(id)
        }

        //for animation recyclerView in bottom sheet
        val recyclerSet = ConstraintSet().apply {
            clone(this@BaseBottomSheet)

            clear(recyclerView?.id!!)
        }

        isShow = margin < parent.height

        //animation for make recyclerView invisible
        animationRecycler = AlphaAnimation(1.0F, 0.0F)
        //set duration = 250 for smoother animation
        animationRecycler.duration = 250

        if(!isShow) {
            recyclerView?.startAnimation(animationRecycler)
            //need delay, because after animation recyclerView alpha = 1F
            Handler().postDelayed({recyclerView?.alpha = 0.0F},250)
        }

        constraintSet.updateMarginTop(margin)

        recyclerSet.updateMarginTopRecycler()

        // start transition
        TransitionManager.beginDelayedTransition(parent, ChangeBounds().apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator() //transition need for animation

        })

        //apply changes in inner content
        constraintSet.applyTo(parent)

        recyclerSet.applyTo(this)

        blockUI(isShow)
    }

    private fun ConstraintSet.updateMarginTopRecycler() {
        recyclerView?.let { recyclerView ->
            fun connectSide(side: Int) = connect(recyclerView.id, side, ConstraintSet.PARENT_ID, side, 0)

            connect(
                recyclerView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, 20.dp
            )

            connectSide(ConstraintSet.BOTTOM)
            connectSide(ConstraintSet.RIGHT)
            connectSide(ConstraintSet.LEFT)
        }
    }

    private fun ConstraintSet.updateMarginTop(margin: Float) {
        fun connectSide(side: Int) = connect(id, side, ConstraintSet.PARENT_ID, side, 0)

        connect(
            id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP, margin.toInt()
        )

        connectSide(ConstraintSet.BOTTOM)
        connectSide(ConstraintSet.LEFT)
        connectSide(ConstraintSet.RIGHT)
    }

    private val contentFromButton = mutableMapOf<Int, BaseRecyclerView>()

    private var recyclerView: BaseRecyclerView? = null

    private fun setRecycler(recyclerView: BaseRecyclerView) {

        if (this.recyclerView?.id != recyclerView.id) {

            // MARK: check if other recycler was added yet - it will be deleted from layout

            if (this.recyclerView != null) {
                removeView(this.recyclerView)
            }

            this.recyclerView = recyclerView

            // MARK: render UI

            removeView(viewLabelBackground)
            removeView(viewLabel)

            subviews(
                recyclerView,
                viewLabelBackground,
                viewLabel
            )

            viewLabelBackground
                .constrainTopToTopOf(this)
                .fillHorizontally()
                .height(20)

            viewLabel
                .width(16)
                .height(6)
                .constrainCenterXToCenterXOf(this)
                .constrainTopToTopOf(this, 8)

            recyclerView
                .constrainTopToTopOf(this,20)
                .constrainBottomToBottomOf(this)
                .fillHorizontally()

            recyclerView.setOnTouchListener(onRecyclerTouchListener)

        }
    }

    private fun blockUI(isVisible: Boolean) {

        if (isVisible) {
            coordinator.blockConstraint.visibility = View.VISIBLE
            coordinator.blockConstraint.setOnClickListener {  }
        } else {
            coordinator.blockConstraint.visibility = View.GONE
        }
    }

    // MARK: work with modules

    fun addRecyclerDataFromButton(viewToCall: View, recyclerView: BaseRecyclerView) {
        contentFromButton[viewToCall.id] = recyclerView
        viewToCall.setOnClickListener(onButtonClickListener)
    }

    fun removeButton(button: View) {
        contentFromButton.remove(button.id)
    }

    // MARK: listeners
    private val onButtonClickListener: OnClickListener
        get() {
            return OnClickListener { view ->
                contentFromButton[view.id]?.let { recyclerView ->

                    setRecycler(recyclerView)
                    //maxHeight bottom sheet in all app
                    val height = (utils.variable.displayHeightPx - utils.variable.navigationBarHeight) * (1 - heightInPercent / 100.0F)

                    slideView(height,500)

                    //start animation in this place because call slideView method 2 times
                    animationRecycler = AlphaAnimation(0.0F, 1.0F)
                    animationRecycler.duration = 700
                    recyclerView?.startAnimation(animationRecycler)

                    Handler().postDelayed({
                        var recyclerHeight = 0

                       //get recyclerView height relatively content
                        for(i in 0 until recyclerView?.adapter?.itemCount!!) {
                            if(recyclerView.layoutManager?.getChildAt(i)?.height != null){
                                recyclerHeight += (recyclerView.layoutManager?.getChildAt(i)?.height)!!
                            }
                        }

                        maxHeight = if (recyclerHeight < height) {
                            (utils.variable.displayHeightPx - utils.variable.navigationBarHeight) - (recyclerHeight + (20 * utils.variable.displayDensity))
                        } else {
                            utils.variable.displayHeightPx * (1 - heightInPercent / 100.0F)
                        }

                        recyclerView.height(((utils.variable.displayHeightPx - maxHeight - utils.variable.navigationBarHeight).toInt() / utils.variable.displayDensity) - 20)

                        slideView(maxHeight,0)
                    },500)
                }
            }
        }

    private val onRecyclerTouchListener: OnTouchListener
        get() {
            return OnTouchListener { recycler, event ->
                if (recycler is RecyclerView) {
                    when(event?.action) {

                        MotionEvent.ACTION_DOWN -> {
                            touchPosition = event.rawY - maxHeight - 20.dp
                        }

                        MotionEvent.ACTION_MOVE -> {
                            recycler.scrollToPosition(event.rawY.toInt())
                        }

                        MotionEvent.ACTION_UP -> {
                            val endPosition = event.rawY - maxHeight

                            val visibleChild = recycler.getChildAt(0)
                            val positionOfChild = recycler.getChildAdapterPosition(visibleChild)

                            if (endPosition > (recycler.height * tresholdForDismiss) && positionOfChild == 0) {
                                slideView(parent.height.toFloat(), duration = 500)
                            }
                        }
                    }
                }

                false
            }
        }

    //area in BottomSheet, in was dismiss
    var tresholdForDismiss = 0.7
    private val onBottomSheetTouchListener: OnTouchListener
        get() {
            return OnTouchListener { _, event ->
                when (event?.action) {

                    MotionEvent.ACTION_DOWN -> { //touch in
                        touchPosition  = event.rawY - (parent.height - this.height - utils.variable.statusBarHeight - utils.variable.actionBarHeight)
                    }

                    MotionEvent.ACTION_MOVE -> { //scroll
                        val marginTop = event.rawY - touchPosition +
                                utils.variable.statusBarHeight + utils.variable.actionBarHeight
                        if(marginTop >= maxHeight)
                            slideView(marginTop, duration = 0)
                    }

                    MotionEvent.ACTION_UP -> { //touch out

                        val height = if(
                            (event.rawY - touchPosition + utils.variable.statusBarHeight
                                    + utils.variable.actionBarHeight) <
                            maxHeight + ((parent.height - maxHeight) * (1.0 - tresholdForDismiss))){
                            maxHeight
                        } else {
                            parent.height.toFloat()
                        }

                        Handler().postDelayed({
                            slideView(height, duration = 500)
                        },50)
                    }
                }
                true
            }
        }
}