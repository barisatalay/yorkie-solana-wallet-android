package com.barisatalay.domain.model

data class TokenModel(
    val code: String,
    val name: String,
    val amount: Double,
    val priceUSDT: Double,
    val icon: String,
    val calculated: Double
) {
//    fun calculate(): String{
//        this.amount * this.priceUSDT
//        val total = try{(this.amount * priceUSDT)} catch (e: Exception){0.0}
//        return "%.2f $".format(total)
//    }
}