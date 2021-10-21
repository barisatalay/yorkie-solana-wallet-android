package com.barisatalay.domain.model.remote.solana

data class SolAccountResult(
        val lamports: Int?,
        val ownerProgram: String?,
        val type: String?,
        val rentEpoch: Int?,
        val account: String?
)