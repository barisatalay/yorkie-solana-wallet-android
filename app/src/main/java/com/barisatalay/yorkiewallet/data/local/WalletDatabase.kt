package com.barisatalay.yorkiewallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.local.dao.WalletDao
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import com.barisatalay.yorkiewallet.data.local.entity.WalletEntity
import com.barisatalay.yorkiewallet.data.local.typeconverter.NetworkTypeConverter

@Database(
    entities = [
        TokenEntity::class,
        WalletEntity::class,
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(
    NetworkTypeConverter::class
)
abstract class WalletDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun walletDao(): WalletDao
}