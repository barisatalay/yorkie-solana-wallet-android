package com.barisatalay.yorkiewallet.di.module

import android.util.Log
import com.barisatalay.domain.Constants
import com.barisatalay.yorkiewallet.BuildConfig
import com.barisatalay.yorkiewallet.data.remote.CustomInterceptor
import com.barisatalay.yorkiewallet.data.remote.RetrofitApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().serializeNulls().create()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.API_BASE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor, customInterceptor: CustomInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    //addInterceptor(loggingInterceptor)
                }
                //addInterceptor(customInterceptor)
                readTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
                connectTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
                writeTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RetrofitApi {
        return retrofit.create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("ApiRequest", "::::**** HttpLoggingInterceptor ")
            Log.d("ApiRequest", message)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideCustomInterceptor() = CustomInterceptor()
}