package com.prenly.newsreader.articlelist

import androidx.lifecycle.*
import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.domain.model.Article

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */
class ArticleListViewModel constructor(private val repository: NewsRepository) : ViewModel() {

    private val _fetchData = MutableLiveData<Boolean>(false)

    val articles: LiveData<List<Article>> = _fetchData.switchMap {
        repository.topHeadlines()
            .toFlowable()
            .toLiveData()
    }

    fun fetchData() {
        _fetchData.value = true
    }
}