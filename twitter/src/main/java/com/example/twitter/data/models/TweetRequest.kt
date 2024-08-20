package com.example.twitter.data.models

import com.google.gson.annotations.SerializedName

class TweetRequest (@SerializedName("text") val text: String)