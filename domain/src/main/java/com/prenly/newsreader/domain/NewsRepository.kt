package com.prenly.newsreader.domain

import com.prenly.newsreader.domain.model.Article
import io.reactivex.Single

interface NewsRepository {

    fun topHeadlines(): Single<List<Article>>

    fun articles(): Single<List<Article>>

    fun articleDetails()
}