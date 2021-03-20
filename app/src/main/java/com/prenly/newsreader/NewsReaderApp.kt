package com.prenly.newsreader

import android.app.Application
import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.repo.NewsApiRepository
import timber.log.Timber

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */
class NewsReaderApp : Application() {

    val newsRepository: NewsRepository by lazy {
        NewsApiRepository()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}