package com.prenly.newsreader.domain

import com.prenly.newsreader.domain.model.Article

interface NewsRepository {

    fun articles(): List<Article>

    fun articleDetails()
}