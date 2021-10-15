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
                //TBA
            }
        }
    }
}