package com.barisatalay.yorkiewallet.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.barisatalay.domain.Constants
import com.barisatalay.yorkiewallet.BuildConfig
import com.barisatalay.yorkiewallet.data.local.WalletDatabase
import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.di.qualifier.MigrationQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, @MigrationQualifier migrations: Set<@JvmSuppressWildcards Migration>): WalletDatabase {
        val builder = Room.databaseBuilder(context, WalletDatabase::class.java, Constants.DB_NAME)
        if (!BuildConfig.DEBUG) builder.fallbackToDestructiveMigration()
        return builder.addMigrations(*migrations.toTypedArray())
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalizationDao(database: WalletDatabase): TokenDao {
        return database.tokenDao()
    }
}