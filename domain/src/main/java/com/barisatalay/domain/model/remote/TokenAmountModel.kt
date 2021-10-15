package com.barisatalay.domain.model.remote

import com.google.gson.annotations.SerializedName

data class TokenAmountModel(
    @SerializedName("amount")
    var amount: String? = null,
    @SerializedName("decimals")
    var decimals: Double? = null,
    @SerializedName("uiAmount")
    var uiAmount: Double? = null,
    @SerializedName("uiAmountString")
    var uiAmountString: String? = null,
)