package com.example.twitter.presentation.model

import com.example.twitter.presentation.Const

class TweetCharacters(
    val text : String = "",
    val typed: Int = 0,
    val remaining: Int = Const.CHARACTERS_LENGTH
) {
    companion object {

        fun fromText(text: String): TweetCharacters {
            val typedCount = text.length
            val remainingCount = Const.CHARACTERS_LENGTH - typedCount
            return TweetCharacters(text = text , typed = typedCount, remaining =  remainingCount)
        }
    }
}