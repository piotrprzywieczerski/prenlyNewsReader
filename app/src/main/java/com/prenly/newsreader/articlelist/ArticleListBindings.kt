package com.prenly.newsreader.articlelist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prenly.newsreader.domain.model.Article
import com.prenly.newsreader.dummy.DummyContent
import timber.log.Timber

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */

object ArticleListBindings {
    @JvmStatic
    @BindingAdapter("articles")
    fun bindArticles(recyclerView: RecyclerView, articles: List<Article>?) {
        articles?.let {
            val adapter = recyclerView.adapter as? ArticleListAdapter
            adapter?.let {
                Timber.d("Piotr setting items")
                adapter.setItems(DummyContent.ITEMS)
            }
        }
    }
}