package com.prenly.newsreader.repo

import com.prenly.newsreader.repo.response.ArticleSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    fun getArticles(@Query("q") query: String): Single<ArticleSearchResult>

    @GET("top-headlines")
    fun getHeadlines(
        @Query("q") query: String?,
        @Query("country") country: String?
    ): Single<ArticleSearchResult>

}