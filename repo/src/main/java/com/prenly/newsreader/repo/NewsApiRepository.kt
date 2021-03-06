package com.prenly.newsreader.repo

import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.domain.model.Article
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.prenly.newsreader.domain.RemoteResource


class NewsApiRepository : NewsRepository {

    private var service: NewsApiService = ApiFactory().createNewsApiService()

    override fun topHeadlines(): Observable<RemoteResource<List<Article>>> {
        return service.getHeadlines(country = "US", query = null)
            .flatMap { searchResult ->
                val articles = searchResult.articles.map {
                    Article(
                        id = it.url ?: "1",
                        title = it.title ?: "title",
                        imageUrl = it.urlToImage ?: "",
                        url = it.url,
                        description = it.description,
                        content = it.content
                    )
                }
                Single.just(RemoteResource.Success(articles) as RemoteResource<List<Article>>)
            }
            .toObservable()
            .startWith(RemoteResource.Loading)
            .onErrorReturn { RemoteResource.Error(it) }
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

