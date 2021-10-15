package com.barisatalay.yorkiewallet

import android.app.Application
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.barisatalay.yorkiewallet.util.bindingadapters.load
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient

@HiltAndroidApp
class WalletApp() : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable: Throwable ->
            Log.d("RxJavaPlugins", throwable.message.orEmpty())
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }.componentRegistry {
                add(SvgDecoder(applicationContext))
            }.build()

    }
}