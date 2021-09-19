package com.fitz.core.usecases

import com.fitz.core.WorkoutTypes
import com.fitz.core.repository.models.Workout
import com.fitz.core.repository.usecases.WorkoutDataAccessor
import com.fitz.core.repository.WorkoutRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * class for bridging data between the repo and the view model
 */

class FitnessDataAccessor @Inject constructor(private val repository: WorkoutRepository):
    WorkoutDataAccessor {
    override suspend fun getAll(): List<Workout> {
        return repository.getAllWorkouts()
    }

    override suspend fun getAll(searchArg: Any): List<Workout> {
        return when(searchArg) {
            is WorkoutTypes -> { repository.getAllWorkoutsByType(searchArg) }
            else -> throw IllegalArgumentException("The searchArg was not one of the supported search params")
        }
    }

    override suspend fun add(addedValue: Workout) {
        repository.addNewWorkout(addedValue)
    }

    override suspend fun delete(itemToDelete: Workout) {
        repository.deleteWorkout(itemToDelete)
    }

}