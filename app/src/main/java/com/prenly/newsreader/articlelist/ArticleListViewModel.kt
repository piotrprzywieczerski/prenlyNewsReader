package com.prenly.newsreader.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prenly.newsreader.domain.model.Article
import timber.log.Timber

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */
class ArticleListViewModel constructor() : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    fun start() {
        // subscribe for repo data
        Timber.d("Piotr start invoked")
        _articles.value = listOf(
            Article("1", "First"),
            Article("2", "Second")
        )
    }
}