    package com.ageone.zenit.Modules.EventMap

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.EventMap.rows.EventMapItemViewHolder
import com.ageone.zenit.Modules.EventMap.rows.EventMapTextViewHolder
import com.ageone.zenit.Modules.EventMap.rows.initialize
import com.ageone.zenit.R
import yummypets.com.stevia.*

class EventMapView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EventMapViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val textMap = arrayOf(
        "Аккредитационный центр",
        "Аккредитационный центр",
        "Карта Газпром Арена")
    val imageMap = arrayOf(
        R.drawable.pic_event_map_1,
        R.drawable.pic_event_map_2,
        R.drawable.pic_event_map_3
    )
    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Карты"
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

        private val EventMapItemType = 0

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            else -> EventMapItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                EventMapItemType -> {
                    EventMapItemViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is EventMapItemViewHolder -> {
                    holder.initialize(imageMap[position], textMap[position])
                    holder.imageViewMap.setOnClickListener {
                        emitEvent?.invoke(EventMapViewModel.EventType.OnPhotoPressed.name)
                    }
                }
            }
        }
    }
}

fun EventMapView.renderUIO() {

    renderBodyTable()
}


