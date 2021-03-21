package com.prenly.newsreader.articlelist

import androidx.lifecycle.*
import com.prenly.newsreader.ArticleListViewModelEvent
import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.domain.model.Article
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

    fun article(id: String): LiveData<Article> {
        return articles.map { articles ->
            (articles as RemoteResource.Success<List<Article>>).data.first { it.url == id }
        }
    }

    fun fetchData() {
        _fetchData.value = true
    }
}