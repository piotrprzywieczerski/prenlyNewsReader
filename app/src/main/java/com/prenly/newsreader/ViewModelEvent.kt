package com.prenly.newsreader

import com.prenly.newsreader.domain.model.Article


data class ArticleListViewModelEvent(
  val articles: List<Article>? = null,
  val error: Throwable? = null,
  val loading: Boolean = false
)

data class ArticleViewModelEvent(
  val article: Article? = null,
  val error: Throwable? = null,
  val loading: Boolean = false
)