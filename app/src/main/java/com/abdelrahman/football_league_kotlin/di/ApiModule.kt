package com.abdelrahman.football_league_kotlin.di

import com.abdelrahman.football_league_kotlin.api.ApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 *@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 *This module responsible for injecting the api

 */
const val BASE_URL = "https://api.football-data.org/v2/"
val apiModule = module {


    single { provideApiService() }

}

fun provideApiService() : ApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(createHttpClient())
        .build()
        .create(ApiService::class.java)
}

fun createHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor { chain ->
            var request = chain.request()
            val builder = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Connection", "close")
            request = builder.build()
            chain.proceed(request)
        }
    return builder.build()
}
