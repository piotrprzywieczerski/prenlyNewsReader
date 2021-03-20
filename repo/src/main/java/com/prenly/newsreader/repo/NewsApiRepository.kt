package com.prenly.newsreader.repo

import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.domain.model.Article
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NewsApiRepository : NewsRepository {

    private var service: NewsApiService = ApiFactory().createNewsApiService()

    override fun topHeadlines(): Single<List<Article>> {
        return service.getHeadlines(country = "US", query = null)
            .flatMap { searchResult ->
                val articles = searchResult.articles.map {
                    Article(it.url ?: "1", it.title ?: "title", it.urlToImage ?: "")
                }
                Single.just(articles)
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun articles(): Single<List<Article>> {
        return service.getArticles(
            query = "apple"
        )
            .doOnSuccess { println("articles: $it") }
            .map { emptyList<Article>() }
    }

    override fun articleDetails() {
        TODO("Not yet implemented")
    }
}

