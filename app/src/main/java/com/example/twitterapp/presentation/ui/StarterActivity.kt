package com.example.twitterapp.presentation.ui

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.core.ui.BaseActivity
import com.example.twitter.presentation.ui.TweetActivity
import com.example.twitterapp.databinding.ActivityStarterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarterActivity : BaseActivity<ActivityStarterBinding>(ActivityStarterBinding::inflate) {

    override fun bindViews() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this , TweetActivity::class.java))
            finish()
        }, 3000)
    }
}