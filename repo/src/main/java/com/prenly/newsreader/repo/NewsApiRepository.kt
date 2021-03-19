package com.prenly.newsreader.repo

import android.annotation.SuppressLint
import com.prenly.newsreader.domain.NewsRepository
import com.prenly.newsreader.domain.model.Article
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager


class NewsApiRepository constructor() : NewsRepository {

    private var service: NewsApiService = createRetrofit()

    override fun articles(): Single<List<Article>> {
        return service.getArticles(
            query = "apple",
            key = "fbf4b25ae3f54703a21cc6da7b618d96"
        )
            .doOnSuccess { println("articles: $it") }
            .map { emptyList<Article>() }
    }

    override fun articleDetails() {
        TODO("Not yet implemented")
    }
}

private fun createRetrofit(): NewsApiService {
    var okClientBuilder = OkHttpClient.Builder()
    okClientBuilder.attachProxy(BuildConfig.HTTP_PROXY_INET, BuildConfig.HTTP_PROXY_PORT)
    okClientBuilder.ignoreHttpsSecurity()

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val moshiConverterFactory = MoshiConverterFactory.create(moshi)


    var retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(moshiConverterFactory)
        .client(okClientBuilder.build())

    var retrofit = retrofitBuilder.build()

    return retrofit.create<NewsApiService>(NewsApiService::class.java)
}


fun OkHttpClient.Builder.attachProxy(inet: String?, port: Int) {
    if (inet != null) {
        val address = InetSocketAddress.createUnresolved(inet, port)
        this.proxy(Proxy(Proxy.Type.HTTP, address))
    }
}

fun OkHttpClient.Builder.ignoreHttpsSecurity() {
    val trust = object : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        @Throws(CertificateException::class)
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Throws(CertificateException::class)
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }
    try {
        val sc = SSLContext.getInstance("SSL")
        sc.init(null, arrayOf(trust), SecureRandom())
        this.sslSocketFactory(sc.socketFactory, trust)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}