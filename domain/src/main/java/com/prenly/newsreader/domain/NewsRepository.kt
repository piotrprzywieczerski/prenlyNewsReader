package com.prenly.newsreader.domain

import com.prenly.newsreader.domain.model.Article
import io.reactivex.Observable
import io.reactivex.Single

interface NewsRepository {

    fun topHeadlines(): Observable<com.prenly.newsreader.domain.RemoteResource<List<Article>>>

    fun articles(): Single<List<Article>>

    fun articleDetails()
}