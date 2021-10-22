package com.barisatalay.yorkiewallet.data.remote

import com.barisatalay.domain.model.remote.solana.SolAccountTokenResponse
import com.barisatalay.domain.model.remote.solana.SolAccountResponse
import com.barisatalay.domain.model.remote.solana.SolMarketPriceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("/account/tokens")
    fun getSolanaWalletBalance(@Query("address") walletAddress: String, @Query("price") price: String): Single<SolAccountTokenResponse>
    @GET("/account")
    fun getSolanaTokenBalance(@Query("address") walletAddress: String): Single<SolAccountResponse>

    @GET("/market")
    fun getMarketPriceFromSol(@Query("symbol") symbol: String): Single<SolMarketPriceResponse>
}