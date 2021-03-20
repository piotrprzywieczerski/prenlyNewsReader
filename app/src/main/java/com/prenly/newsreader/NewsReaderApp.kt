package com.prenly.newsreader

import android.app.Application
import timber.log.Timber

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */
class NewsReaderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}