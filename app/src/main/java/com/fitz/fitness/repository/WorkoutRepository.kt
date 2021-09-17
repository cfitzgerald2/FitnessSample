package com.fitz.fitness.repository

import com.fitz.fitness.WorkoutTypes

class WorkoutRepository(val workoutDAO: WorkoutDAO) {

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