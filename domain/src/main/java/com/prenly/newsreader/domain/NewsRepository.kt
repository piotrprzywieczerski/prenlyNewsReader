package com.prenly.newsreader.domain

import com.prenly.newsreader.domain.model.Article
import io.reactivex.Single

interface NewsRepository {

    fun articles(): Single<List<Article>>

    fun articleDetails()
}