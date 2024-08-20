package com.example.twitter.presentation.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.core.network.utils.DataState
import com.example.core.ui.BaseActivity
import com.example.twitter.R
import com.example.twitter.databinding.ActivityTweetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TweetActivity : BaseActivity<ActivityTweetBinding>(ActivityTweetBinding::inflate),
    TweetHandler {

    private val tweetViewModel by viewModels<TweetViewModel>()

    override fun bindViews() {
        binding.handler = this
        watchTweet()
        observe()
    }

    private fun watchTweet() {
        binding.tweetInputEditText.addTextChangedListener {
            tweetViewModel.onTweetTextUpdated(
                it?.toString() ?: ""
            )

            binding.buttonEnabled = !it.isNullOrEmpty()
        }
    }

    private fun observe() {
        tweetViewModel.tweetCharactersLiveData.observe(this) {
            binding.textCountTextView.text = getString(R.string._280, it.typed.toString())
            binding.textRemainingValue.text = it.remaining.toString()
        }

        tweetViewModel.buttonEnabledLiveData.observe(this) {
            binding.tweetButtonEnabled = it
        }

        tweetViewModel.postTweetState.observe(this) {
            when (it) {
                is DataState.Failure ->{
                    binding.progress.visibility = View.GONE
                    handleError(it.throwable)
                }
                DataState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }

                is DataState.Success -> {
                    binding.progress.visibility = View.GONE
                    showMessage(getString(R.string.post_tweet_successfuly, it.data.id))
                }
            }
        }
    }

    override fun onDeleteButtonClicked() {
        binding.tweetInputEditText.setText("")
    }

    override fun onCopyButtonClicked() {
        val clipboardManager = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("tweet", binding.tweetInputEditText.text.toString())
        clipboardManager.setPrimaryClip(clipData)
    }

    override fun onPostTweetButtonClicked() {
        tweetViewModel.postTweet()
    }

    override fun onBackButtonClicked() {
        finish()
    }

}