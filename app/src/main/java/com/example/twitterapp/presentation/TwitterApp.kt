package com.example.twitterapp.presentation

import android.app.Application
import com.example.twitterapp.BuildConfig
import com.pluto.Pluto
import com.pluto.plugins.network.PlutoNetworkPlugin
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TwitterApp : Application(){

    override fun onCreate() {
        super.onCreate()
        installPluto()
        installTimber()
    }

    private fun installPluto() {
        Pluto.Installer(this)
            .addPlugin(PlutoNetworkPlugin())
            .install()
    }

    private fun installTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}