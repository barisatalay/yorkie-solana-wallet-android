package com.barisatalay.domain.model.remote

import com.google.gson.annotations.SerializedName

open class BaseResponse<T> {
    @field:SerializedName("succcess")
    var succcess: Boolean? = null

    @SerializedName("data")
    var data: T? = null
}