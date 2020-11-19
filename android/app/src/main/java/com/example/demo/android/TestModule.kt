package com.example.demo.android

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy

@Module
@InstallIn(ActivityComponent::class)
object TestModule {

    @Provides
    fun okHttpClient(
        application: Application
    ): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
        })
        .addNetworkInterceptor(ChuckerInterceptor(application))
        .cookieJar(JavaNetCookieJar(CookieManager().apply {
            setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        }))
        .followRedirects(false)
        .followSslRedirects(false)
        .build()

    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://example.com")
        .client(okHttpClient)
        .build()

    @Provides
    fun testClient(retrofit: Retrofit): TestClient = retrofit.create(TestClient::class.java)
}