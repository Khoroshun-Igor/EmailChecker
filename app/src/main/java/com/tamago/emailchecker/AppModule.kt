package com.tamago.emailchecker

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.tamago.emailchecker.BuildConfig.*
import com.tamago.kickboxioapi.BouncerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */

//dependency injection using hilt: okhttp (adding header) and retrofit
//the api key and url are included in the build config
//converter factory - kotlinx.serialization
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("OkHttp")
    fun provideOkHttp(@ApplicationContext context: Context): OkHttpClient {
        val apiKey = BOUNCER_API_KEY
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .addHeader("x-api-key", apiKey)
                        .build()
                )
            }
            .build()
    }

    @Provides
    @Named("Retrofit")
    fun provideRetrofit(@Named("OkHttp") okHttpClient: OkHttpClient): Retrofit {
        val baseUrl = BOUNCER_BASE_URL
        val converterFactory = Json.asConverterFactory("application/json".toMediaType())
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideBouncerService(@Named("Retrofit") retrofit: Retrofit): BouncerApi {
        return retrofit.create(BouncerApi::class.java)
    }
}