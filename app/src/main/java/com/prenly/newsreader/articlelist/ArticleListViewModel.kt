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
            Article("1", "NBA", "https://a2.espncdn.com/combiner/i?img=%2Fphoto%2F2021%2F0320%2Fr829434_1296x729_16%2D9.jpg"),
            Article("2", "Bitcoin","https://cdn.vox-cdn.com/thumbor/IdgNJaOIQBsN8QbQcH2MDU6sAUA=/0x243:2040x1311/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/10432811/mdoying_180308_2373_0091still.jpg")
        )
    }
}