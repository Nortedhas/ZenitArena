package com.ageone.zenit.Modules.Event

import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Event.rows.EventItemViewHolder
import com.ageone.zenit.Modules.Event.rows.EventTextViewHolder
import com.ageone.zenit.Modules.Event.rows.initialize
import com.ageone.zenit.UIComponents.ViewHolders.EventViewHolder
import com.ageone.zenit.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class EventView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = EventViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val text = arrayOf(
        " РПЛ, «Зенит» - «Ростов»",
        "Кубок России, «Зенит» - «Томь»",
        "РПЛ, «Зенит» - «ЦСКА»",
        "Лига Чемпионов, «Зенит» - «РБ Лейпциг»",
        "квалификация ЧЕ-2020, «Россия» - «Бельгия»",
        "Лига Чемпионов, «Зенит» - «Лион»",
        "квалификация ЧЕ-2020, «Россия» - «Бельгия»",
        "Лига Чемпионов, «Зенит» - «Лион»",
        "РПЛ, «Зенит» - «ЦСКА»")

    val monthMap = mutableMapOf<Int,String>()

    init {
//        viewModel.loadRealmData()

        monthMap[0] = "Октябрь" //TODO : for test
        monthMap[3] = "Ноябрь"

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "События"
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

        private val EventTextType = 0
        private val EventItemType = 1

        override fun getItemCount() = 9//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0,3 -> EventTextType
            else -> EventItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                EventTextType -> {
                    EventTextViewHolder(layout)
                }
                EventItemType -> {
                EventViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is EventTextViewHolder -> {
                    holder.initialize(monthMap[position]!!)
                }
                is EventViewHolder -> {
                    if(position == 1) {
                        holder.textViewEvent.typeface = Typeface.DEFAULT_BOLD
                        holder.shape.setColor(Color.parseColor("#C6F0FF"))
                        holder.viewLine.backgroundColor = Color.parseColor("#5ED4FF")
                        holder.viewLine.initialize()

                        holder.viewBack.setOnClickListener {
                            emitEvent?.invoke(EventViewModel.EventType.OnEventPressed.name)
                        }
                    }
                    holder.initialize("dd.MM.yyyy, HH:MM",1571511600,text[position])
                }
            }
        }
    }
}

fun EventView.renderUIO() {

    renderBodyTable()
}


