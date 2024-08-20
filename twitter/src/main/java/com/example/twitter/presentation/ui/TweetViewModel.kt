package com.example.twitter.presentation.ui

import android.content.ClipData
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.utils.DataState
import com.example.twitter.data.models.TweetResponse
import com.example.twitter.domain.repositories.TweetRepository
import com.example.twitter.presentation.Const
import com.example.twitter.presentation.model.TweetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.security.AccessController.getContext
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(private val tweetRepository: TweetRepository) :
    ViewModel() {

    private val _tweetCharactersLiveData: MutableLiveData<TweetCharacters> = MutableLiveData(
        TweetCharacters()
    )

    val tweetCharactersLiveData: LiveData<TweetCharacters> get() = _tweetCharactersLiveData

    private val _buttonEnabledLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val buttonEnabledLiveData: LiveData<Boolean?> get() = _buttonEnabledLiveData

    private val _postTweetState: MutableLiveData<DataState<TweetResponse>> = MutableLiveData()

    val postTweetState: LiveData<DataState<TweetResponse>> get() = _postTweetState

    fun onTweetTextUpdated(text: String) {
        val tweetCharacters = TweetCharacters.fromText(text)
        _tweetCharactersLiveData.value = tweetCharacters
        when {
            tweetCharacters.typed > Const.CHARACTERS_LENGTH -> {
                _buttonEnabledLiveData.value = false
            }

            text.isEmpty() ->{
                _buttonEnabledLiveData.value = false
            }

            else -> {
                _buttonEnabledLiveData.value = true
            }
        }

    }

    fun postTweet() {
        viewModelScope.launch {
            _tweetCharactersLiveData.value?.text?.let { tweetText ->
                tweetRepository.postTweet(tweetText)
                    .onStart {
                        _postTweetState.value = DataState.Loading
                    }
                    .catch {
                        _postTweetState.value = DataState.Failure(it)
                    }
                    .collect {
                        _postTweetState.value = DataState.Success(it)
                    }
            }
        }
    }
}