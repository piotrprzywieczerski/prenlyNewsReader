package com.prenly.newsreader.repo

import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.domain.model.Article
import io.reactivex.Single


class NewsApiRepository : NewsRepository {

    private var service: NewsApiService = ApiFactory().createNewsApiService()

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

