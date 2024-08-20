package com.example.twitter.data.repositories

import com.example.twitter.data.datasource.remote.TwitterAPI
import com.example.twitter.data.models.TweetRequest
import com.example.twitter.data.models.TweetResponse
import com.example.twitter.domain.repositories.TweetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TweetRepositoryImpl(private val twitterAPI : TwitterAPI) : TweetRepository {
    override fun postTweet(text: String): Flow<TweetResponse> = flow {
        val response = twitterAPI.postTweet(request = TweetRequest(text = text) , authHeader = "Bearer AAAAAAAAAAAAAAAAAAAAAIHKvQEAAAAA4BPjrX89L9%2B8Ds1kK7uKZFChb9o%3DBIA0qbheL39VQNY6W1ndoSzOtVMSzxv7AXhoY22p91IeLtkOXi")
        emit(response.data)
    }.flowOn(Dispatchers.IO)
}