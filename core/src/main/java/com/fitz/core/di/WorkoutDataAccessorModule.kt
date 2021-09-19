package com.fitz.core.di

import com.fitz.core.repository.usecases.WorkoutDataAccessor
import com.fitz.core.usecases.FitnessDataAccessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * class for binding (telling dagger what to provide) implementations to the base class/interface
 * it implements
 */

@Suppress("unused")
@InstallIn(ViewModelComponent::class)
@Module
abstract class WorkoutDataAccessorModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsFitnessDataAccessor(dataAccessor: FitnessDataAccessor): WorkoutDataAccessor
}