package com.prenly.newsreader.repo

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/everything")
    fun getArticles(@Query("apiKey") key: String): Single<ResponseBody>

}