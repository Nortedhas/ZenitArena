package com.ageone.zenit.Modules.News

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.News.rows.NewsItemViewHolder
import com.ageone.zenit.Modules.News.rows.initialize
import com.ageone.zenit.R
import yummypets.com.stevia.*

class NewsView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = NewsViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val newsPicture = arrayOf(
        R.drawable.pic_news_1,
        R.drawable.pic_item_1)

    val date = arrayOf(1572393600, 1569196800)

    val newsTitle = arrayOf(
        "Подготовимся к матчу вместе!",
        "Матч Кубка России ")

    val news = arrayOf(
        "Если вы с нами впервые или давно не были в качестве волонтера...",
        "Футбольне клубы встречались на поле 19 раз, из которых ФК..."
    )

    val imageViewFAB by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.setImageResource(R.drawable.ic_fab)
        imageView.initialize()
        imageView
    }

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = "Новости"
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

        private val NewsItemType = 0

        override fun getItemCount() = 2//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            else -> NewsItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                NewsItemType -> {
                    NewsItemViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is NewsItemViewHolder -> {
                    holder.initialize(
                        newsPicture[position],
                        date[position],
                        newsTitle[position],
                        news[position])

                    holder.viewBackContinue.setOnClickListener {
                        emitEvent?.invoke(NewsViewModel.EventType.OnContinuePressed.name)
                    }
                }

            }

        }

    }

}

fun NewsView.renderUIO() {

    innerContent.subviews(
        imageViewFAB
    )

    imageViewFAB
        .constrainBottomToBottomOf(innerContent,54)
        .constrainRightToRightOf(innerContent, 18)
        .height(55)
        .width(55)

    renderBodyTable()
}


