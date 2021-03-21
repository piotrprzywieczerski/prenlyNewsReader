package com.prenly.newsreader.articlelist

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prenly.newsreader.ArticleListViewModelEvent
import com.prenly.newsreader.ArticleViewModelEvent
import com.prenly.newsreader.GlideApp

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */

object ArticleListBindings {

    @JvmStatic
    @BindingAdapter("articles")
    fun bindArticles(recyclerView: RecyclerView, articles: ArticleListViewModelEvent?) {
        articles?.articles?.let { articleList ->
            recyclerView.visibility = View.VISIBLE
            val adapter = recyclerView.adapter as? ArticleListAdapter
            adapter?.let {
                adapter.submitList(articleList)
            }
        } ?: run {
            recyclerView.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("loading")
    fun loading(progressBar: ProgressBar, articles: ArticleListViewModelEvent?) {
        if (articles?.loading == true) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("loading")
    fun loading(progressBar: ProgressBar, isLoading: Boolean?) {
        if (isLoading == true) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
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
    @BindingAdapter("imageUrl")
    fun bindArticles2(imageView: ImageView, articleEvent: ArticleViewModelEvent?) {
        articleEvent?.article?.let {
            imageView.visibility = View.VISIBLE
            GlideApp.with(imageView.context)
                .load(it.imageUrl)
                .into(imageView)
        } ?: run {
            imageView.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("goneWhenTrue")
    fun goneWhenTrue(view: View, isGone: Boolean?) {
        if (isGone == true) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }
}