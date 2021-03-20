package com.prenly.newsreader.repo

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Piotr Przywieczerski on 19.03.2021.
 */
class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithApiKeyHeader = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", "fbf4b25ae3f54703a21cc6da7b618d96")
            .build()

        return chain.proceed(requestWithApiKeyHeader)
    }
}