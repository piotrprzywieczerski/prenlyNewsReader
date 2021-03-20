package com.prenly.newsreader.domain.model

data class Article(
    val id: String,
    val url: String?,
    val title: String,
    val description: String?,
    val content: String?,
    val imageUrl: String
)