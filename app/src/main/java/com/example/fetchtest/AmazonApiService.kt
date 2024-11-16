package com.example.fetchtest

import com.example.fetchtest.data.OneItem
import retrofit2.Response
import retrofit2.http.GET

interface AmazonApiService {
    @GET("hiring.json")
    suspend fun getItems(): Response<List<OneItem>>
}
