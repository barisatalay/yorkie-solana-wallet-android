package com.barisatalay.yorkiewallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity

@Database(
    entities = [
        TokenEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class WalletDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao

}