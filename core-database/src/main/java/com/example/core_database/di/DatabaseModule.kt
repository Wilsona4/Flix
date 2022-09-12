/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.core_database.di

import android.content.Context
import androidx.room.Room
import com.example.core_database.FlixDatabase
import com.example.core_database.FlixDatabase.Companion.FLIX_DATABASE
import com.example.core_database.dao.PopularDao
import com.example.core_database.dao.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesFlixDatabase(
        @ApplicationContext context: Context,
    ): FlixDatabase = Room.databaseBuilder(
        context,
        FlixDatabase::class.java,
        FLIX_DATABASE
    ).build()

    @Singleton
    @Provides
    fun providesPopularDAO(database: FlixDatabase): PopularDao = database.popularDao()

    @Singleton
    @Provides
    fun providesRemoteKeysDAO(database: FlixDatabase): RemoteKeysDao = database.remoteKeysDao()
}
