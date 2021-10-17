package com.barisatalay.yorkiewallet.di.module

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.barisatalay.yorkiewallet.di.qualifier.MigrationQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object MigrationModule {
    @IntoSet
    @Provides
    @MigrationQualifier
    fun migrate1to2(): Migration {
        return object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //region ..:: Token Table New Colum Renamed ::..
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `Token_new` (
                    `walletAddress` TEXT NOT NULL, 
                    `contractAddress` TEXT NOT NULL, 
                    `code` TEXT NOT NULL, 
                    `name` TEXT NOT NULL, 
                    `icon` TEXT NOT NULL, 
                    `amount` REAL NOT NULL, 
                    `priceUsdt` REAL NOT NULL, 
                    `lamports` INTEGER NOT NULL, 
                    `decimals` REAL NOT NULL, 
                    PRIMARY KEY(`walletAddress`, `contractAddress`))
            """.trimIndent())

                database.execSQL("""
                INSERT INTO Token_new (walletAddress, contractAddress, code, name, icon, amount, priceUsdt, lamports, decimals)
                SELECT walletId, address, code, name, icon, amount, pricaUsdt, lamports, decimals FROM Token
                """.trimIndent())
                database.execSQL("DROP TABLE Token")
                database.execSQL("ALTER TABLE Token_new RENAME TO Token")
                //endregion


                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `Wallet` (`address` TEXT NOT NULL, 
                    `label` TEXT NOT NULL, 
                    `timestamp` INTEGER NOT NULL, 
                    `networkType` TEXT NOT NULL, 
                    `isActive` INTEGER NOT NULL, 
                    PRIMARY KEY(`address`)
                    )
            """.trimIndent())
            }
        }
    }
}