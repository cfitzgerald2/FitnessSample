package com.fitz.core.repository

import com.fitz.core.WorkoutTypes
import com.fitz.core.repository.dao.WorkoutDAO
import com.fitz.core.repository.models.Workout
import javax.inject.Inject
import javax.inject.Singleton

/**
 * class for holding the data for workouts
 */
@Singleton
class WorkoutRepository @Inject constructor(private val workoutDAO: WorkoutDAO) {

    suspend fun getAllWorkouts(): List<Workout> {
        return workoutDAO.getWorkouts()
    }

    suspend fun getAllWorkoutsByType(type: WorkoutTypes): List<Workout> {
        return workoutDAO.getWorkoutByType(type)
    }

    suspend fun addNewWorkout(workout: Workout) {
        workoutDAO.addWorkout(workout = workout)
    }

    suspend fun deleteWorkout(workout: Workout) {
        workoutDAO.deleteWorkout(workout = workout)
    }
}