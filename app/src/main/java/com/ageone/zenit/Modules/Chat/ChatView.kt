package com.ageone.zenit.Modules.Chat

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.View.BaseView
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Chat.rows.ChatMessageViewHolder
import com.ageone.zenit.Modules.Chat.rows.initialize
import yummypets.com.stevia.*

class ChatView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ChatViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }
    
    val viewSendMessage by lazy {
        val view = BaseView()
        view.cornerRadius = 1000.dp
        view.backgroundColor = Color.parseColor("#F2F2F2")
        view.borderColor = Color.parseColor("#E4E4E4")
        view.borderWidth = 1
        view.initialize()
    // 	imageView.elevation = 5F.dp
        view
    }

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Волонтеры камер хранения"

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

        private val ChatMessageType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ChatMessageType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ChatMessageType -> {
                    ChatMessageViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is ChatMessageViewHolder -> {
                    holder.initialize()
                }

            }

        }

    }

}

fun ChatView.renderUIO() {

    innerContent.subviews(
        bodyTable,
        viewSendMessage
    )

    viewSendMessage
        .fillHorizontally(16)
        .height(utils.tools.getActualSizeFromDes(42))
        .constrainBottomToBottomOf(innerContent, 16)

    bodyTable
        .fillHorizontally()
        .constrainTopToTopOf(innerContent)
        .constrainBottomToTopOf(viewSendMessage, 16)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false
}


