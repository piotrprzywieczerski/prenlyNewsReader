package com.prenly.newsreader.articlelist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prenly.newsreader.GlideApp
import com.prenly.newsreader.domain.model.Article

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
                adapter.submitList(articles)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindArticles(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            GlideApp.with(imageView.context)
                .load(imageUrl)
                .centerCrop()
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl2")
    fun bindArticles2(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            GlideApp.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}