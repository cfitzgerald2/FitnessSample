package com.fitz.core.di

import android.content.Context
import com.fitz.core.repository.dao.WorkoutDAO
import com.fitz.core.repository.database.FitnessDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Class for providing (creating) dependencies needed by other classes for the dependency graph.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FitnessDatabase {
        return FitnessDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideWorkoutDAO(database: FitnessDatabase): WorkoutDAO {
        return database.workoutDAO()
    }
}