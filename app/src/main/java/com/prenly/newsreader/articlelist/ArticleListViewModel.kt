package com.prenly.newsreader.articlelist

import androidx.lifecycle.*
import com.prenly.newsreader.ArticleListViewModelEvent
import com.prenly.newsreader.ArticleViewModelEvent
import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.domain.RemoteResource
import io.reactivex.BackpressureStrategy

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */
class ArticleListViewModel constructor(private val repository: NewsRepository) : ViewModel() {

    private val _fetchData = MutableLiveData<Boolean>(false)

    val articles: LiveData<ArticleListViewModelEvent> = _fetchData.switchMap {
        repository.topHeadlines()
            .toFlowable(BackpressureStrategy.LATEST)
            .map {
                when (it) {
                    is RemoteResource.Success -> {
                        ArticleListViewModelEvent(
                            it.data
                        )
                    }
                    is RemoteResource.Error -> ArticleListViewModelEvent(error = it.exception)
                    RemoteResource.Loading -> ArticleListViewModelEvent(loading = true)
                }
            }
            .toLiveData()
    }

    fun article(id: String): LiveData<ArticleViewModelEvent> {
        return articles.map { articlesEvent ->
            when {
                articlesEvent.articles != null -> {
                    val article = articlesEvent.articles.firstOrNull { it.url == id }
                    article?.let {
                        ArticleViewModelEvent(article)
                    } ?: kotlin.run {
                        ArticleViewModelEvent(error = IllegalStateException("Cannot find article"))
                    }
                }
                articlesEvent.loading -> {
                    ArticleViewModelEvent(loading = true)
                }
                else -> {
                    ArticleViewModelEvent(error = articlesEvent.error)
                }
            }
        }
    }

    fun fetchData() {
        _fetchData.value = true
    }
}