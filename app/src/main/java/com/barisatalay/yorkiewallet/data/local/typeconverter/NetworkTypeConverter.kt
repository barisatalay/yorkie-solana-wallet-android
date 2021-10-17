package com.barisatalay.yorkiewallet.data.local.typeconverter

import androidx.room.TypeConverter
import com.barisatalay.domain.model.NetworkType

class NetworkTypeConverter {
    @TypeConverter
    fun fromStringToBadgeType(value:String?): NetworkType? {
        return NetworkType.find(value)
    }

    @TypeConverter
    fun fromBadgeTypeToString(type: NetworkType?): String? {
        return type?.name
    }

}