package com.prenly.newsreader.repo

import android.util.Log
import org.junit.Assert
import org.junit.Test
import com.prenly.newsreader.repo.NewsApiRepository as Repository

class NewsApiRepositoryTest {

    private val newsApiRepository = Repository()

    @Test
    fun articles() {
        val response = newsApiRepository.articles()
            .blockingGet()

        Log.d("Piotr", "Piotr response: $response")
        Assert.assertNotNull(response)
    }

    @Test
    fun articleDetails() {
    }
}