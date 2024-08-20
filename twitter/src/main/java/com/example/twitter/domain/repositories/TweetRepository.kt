package com.example.twitter.domain.repositories

import com.example.twitter.data.models.TweetResponse
import kotlinx.coroutines.flow.Flow

fun interface TweetRepository {

    fun postTweet(text : String) : Flow<TweetResponse>
}