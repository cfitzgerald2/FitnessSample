package com.fitz.fitness.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.fitz.fitness.WorkoutTypes
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDAO {

    @Query("SELECT * FROM workout WHERE workout_name = :type")
    suspend fun getWorkoutByType(type: WorkoutTypes): List<Workout>

    @Query("SELECT * FROM workout")
    suspend fun getWorkouts(): List<Workout>

    @Insert(onConflict = REPLACE)
    suspend fun addWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)
}