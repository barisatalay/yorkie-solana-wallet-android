package com.barisatalay.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

enum class NetworkType(val value: String) {
    @SerializedName("unknown")
    Unknown("unknown"),

    @SerializedName("solana")
    SOLANA("solana");

    companion object {
        fun find(value: String?): NetworkType {
            return values().find {
                it.value.lowercase(Locale.US) == value?.lowercase(Locale.US)
            } ?: kotlin.run { Unknown }
        }
    }
}