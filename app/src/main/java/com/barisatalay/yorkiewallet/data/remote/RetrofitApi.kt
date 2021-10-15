package com.barisatalay.yorkiewallet.data.remote

import com.barisatalay.domain.model.remote.AccountTokenResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("/account/tokens")
    fun getWalletBalance(@Query("address") walletAddress: String, @Query("price") price: String): Single<AccountTokenResponse>

}