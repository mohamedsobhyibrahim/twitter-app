package com.example.twitter.data.datasource.remote

import com.example.core.network.data.models.ApiResponse
import com.example.twitter.data.EndPoints
import com.example.twitter.data.models.TweetRequest
import com.example.twitter.data.models.TweetResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

fun interface TwitterAPI {

    @POST(EndPoints.POST_TWEET)
    suspend fun postTweet(@Body request: TweetRequest ,
                          @Header("Authorization") authHeader: String): ApiResponse<TweetResponse>
}