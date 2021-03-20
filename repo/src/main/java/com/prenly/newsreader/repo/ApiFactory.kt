package com.prenly.newsreader.repo

import android.annotation.SuppressLint
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
import kotlin.jvm.Throws

/**
 * Created by Piotr Przywieczerski on 19.03.2021.
 */
class ApiFactory {
    fun createNewsApiService(): NewsApiService {
        var okClientBuilder = OkHttpClient.Builder()
        okClientBuilder.attachProxy(BuildConfig.HTTP_PROXY_INET, BuildConfig.HTTP_PROXY_PORT)
        okClientBuilder.ignoreHttpsSecurity()
        okClientBuilder.addInterceptor(ApiKeyInterceptor())

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
}