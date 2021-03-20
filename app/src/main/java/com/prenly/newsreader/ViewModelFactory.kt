package com.prenly.newsreader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prenly.newsreader.articlelist.ArticleListViewModel

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */
class ViewModelFactory(private val application: NewsReaderApp) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = application.newsRepository
        return when {
            ArticleListViewModel::class.java.isAssignableFrom(modelClass) -> {
                ArticleListViewModel(repository)
            }

            else -> throw IllegalStateException("Cannot match viewModel to $modelClass")
        } as T
    }
}