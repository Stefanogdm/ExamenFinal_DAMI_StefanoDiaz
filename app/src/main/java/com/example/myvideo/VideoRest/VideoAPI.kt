package com.example.myvideo.VideoRest

import retrofit2.Response
import retrofit2.http.GET

interface VideoAPI {
    @GET("v1/043bab31-9709-4cc1-921f-21d9a051ce3a")
    suspend fun getCategory(): Response<Category>
}
