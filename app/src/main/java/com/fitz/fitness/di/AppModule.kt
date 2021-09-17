package com.fitz.fitness.di

import android.content.Context
import com.fitz.fitness.repository.FitnessDatabase
import com.fitz.fitness.repository.WorkoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FitnessDatabase {
        return FitnessDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideRepository(@ApplicationContext context: Context): WorkoutRepository {
        return WorkoutRepository(provideDatabase(context).workoutDAO())
    }
}