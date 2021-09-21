package com.fitz.core.repository.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.fitz.core.WorkoutTypes
import com.fitz.core.repository.models.Workout

@Dao
interface WorkoutDAO {

    @Query("SELECT * FROM workout WHERE workout_name = :type")
    suspend fun getWorkoutByType(type: WorkoutTypes): List<Workout>

    @Query("SELECT * FROM workout")
    suspend fun getWorkouts(): List<Workout>

    @Insert(onConflict = REPLACE)
    suspend fun addWorkout(workout: Workout)

    @Update(onConflict = REPLACE)
    suspend fun updateWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)
}