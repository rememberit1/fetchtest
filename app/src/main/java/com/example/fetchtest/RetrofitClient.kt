package com.example.fetchtest

import com.example.fetchtest.data.ConstantsAmazon
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val  okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val amazonApiService: AmazonApiService by lazy{
        Retrofit.Builder()
            .baseUrl(ConstantsAmazon.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(AmazonApiService::class.java)
    }


}