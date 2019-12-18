package com.ageone.zenit.Modules.Restaurant.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.KitchenTextViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*
import com.ageone.zenit.External.Base.RecyclerView.HorizontalSpacesItemDecoration
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.RxBus.RxBus
import com.ageone.zenit.Models.RxEvent
import com.ageone.zenit.SCAG.Category
import timber.log.Timber

class RestaurantTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    var kitchenList = listOf<Category>()

    var selectedCategory = 0

    val recyclerViewKitchen by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }

    val textViewCurrentCategory by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    var onTap: ((Int) -> Unit)? = null

    init {
        recyclerViewKitchen.adapter = viewAdapter
        recyclerViewKitchen.layoutManager =
            LinearLayoutManager(
                currentActivity,
                LinearLayoutManager.HORIZONTAL, false)

        recyclerViewKitchen.overScrollMode = View.OVER_SCROLL_NEVER

        val space = 8.dp
        recyclerViewKitchen.addItemDecoration(HorizontalSpacesItemDecoration(space))

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewKitchen)

        renderUI()
    }

    inner class Factory: RecyclerView.Adapter<KitchenTextViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitchenTextViewHolder {
            val layout = ConstraintLayout(parent.context)
            layout
                .width(matchParent)
                .height(wrapContent)

            return KitchenTextViewHolder(layout)
        }

        override fun getItemCount(): Int = kitchenList.size

        override fun onBindViewHolder(holder: KitchenTextViewHolder, position: Int) {
            if (position in kitchenList.indices) {
                val category = kitchenList[position]

                holder.initialize(category.name,position == selectedCategory)
                holder.constraintLayout.setOnClickListener {
                    selectedCategory = position
                    notifyDataSetChanged()
                    RxBus.publish(RxEvent.EventChangeCategory(selectedCategory))
                }
            }

        }
    }
}

fun RestaurantTextViewHolder.renderUI() {
    constraintLayout.subviews(
        recyclerViewKitchen,
        textViewCurrentCategory
    )

    recyclerViewKitchen
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout)

    textViewCurrentCategory
        .constrainTopToBottomOf(recyclerViewKitchen,10)
        .constrainLeftToLeftOf(constraintLayout,16)
}

fun RestaurantTextViewHolder.initialize(categories: List<Category>) {
    Timber.i("Categories in init: ${categories.size}")
    kitchenList = categories
    Timber.i("kitchenList in init: ${kitchenList.size}")
    recyclerViewKitchen.adapter?.notifyDataSetChanged()

    if (kitchenList.isNotEmpty()) {
        textViewCurrentCategory.text = kitchenList[selectedCategory].name
    }
}
