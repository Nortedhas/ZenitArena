package com.ageone.zenit.Modules.Messages

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Messages.rows.MessagesChatViewHolder
import com.ageone.zenit.Modules.Messages.rows.initialize
import com.ageone.zenit.R
import yummypets.com.stevia.*

class MessagesView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MessagesViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Сообщения"
        toolbar.textColor = Color.parseColor("#00ACEB")

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

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

        private val MessagesChatType = 0

        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            in 0..2 -> MessagesChatType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                MessagesChatType -> {
                    MessagesChatViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is MessagesChatViewHolder -> {
                    val (icon, title) = when(position) {
                        1 -> R.drawable.ic_chat_vc to "Чат с ВЦ"
                        2 -> R.drawable.ic_chat_common to "Общий чат"
                        else -> R.drawable.ic_chat_func2 to "Волонтеры камер хранения"
                    }
                    holder.initialize(icon, title, position == 2, position == 0)
                    holder.constraintLayout.setOnClickListener {
                        emitEvent?.invoke(MessagesViewModel.EventType.OnChatPressed.name)
                    }
                }

            }

        }

    }

}

fun MessagesView.renderUIO() {

    renderBodyTable()
}


