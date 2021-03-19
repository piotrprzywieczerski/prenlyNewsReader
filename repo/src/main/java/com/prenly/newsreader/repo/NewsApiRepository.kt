package com.prenly.newsreader.repo

import android.util.Log
import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.domain.model.Article
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class NewsApiRepository constructor() : NewsRepository {

    private var service: NewsApiService

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        service = retrofit.create<NewsApiService>(NewsApiService::class.java)
    }


    override fun articles(): Single<List<Article>> {
        return service.getArticles("fbf4b25ae3f54703a21cc6da7b618d96")
            .doOnSuccess { Log.d("Piotr", "articles: $it") }
            .map { emptyList<Article>() }
    }

    override fun articleDetails() {
        TODO("Not yet implemented")
    }
}