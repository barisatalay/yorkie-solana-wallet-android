package com.barisatalay.domain.model.remote.solana

import com.barisatalay.domain.model.remote.TokenAmountModel
import com.google.gson.annotations.SerializedName

data class SolAccountTokenResult(
        @SerializedName("tokenAddress")
        var tokenAddress: String? = null,
        @SerializedName("tokenAmount")
        var tokenAmount: TokenAmountModel? = null,
        @SerializedName("tokenAccount")
        var tokenAccount: String? = null,
        @SerializedName("tokenName")
        var tokenName: String? = null,
        @SerializedName("tokenIcon")
        var tokenIcon: String? = null,
        @SerializedName("rentEpoch")
        var rentEpoch: Int? = null,
        @SerializedName("lamports")
        var lamports: Int? = null,
        @SerializedName("tokenSymbol")
        var tokenSymbol: String? = null,
        @SerializedName("priceUsdt")
        var priceUsdt: Double? = null
)