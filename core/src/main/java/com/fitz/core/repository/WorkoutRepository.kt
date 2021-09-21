package com.fitz.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fitz.core.WorkoutTypes
import com.fitz.core.repository.dao.WorkoutDAO
import com.fitz.core.repository.models.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

/**
 * class for holding the data for workouts
 */
@Singleton
class WorkoutRepository @Inject constructor(private val workoutDAO: WorkoutDAO) {

    private var allWorkouts: List<Workout>? = null
    private val _allWorkoutsObservable: MutableLiveData<List<Workout>> = MutableLiveData()
    val allWorkoutsObservable: LiveData<List<Workout>> = _allWorkoutsObservable

    suspend fun getAllWorkouts(): List<Workout> {
        val all = allWorkouts ?: workoutDAO.getWorkouts()
        _allWorkoutsObservable.postValue(all)
        return all
    }

    suspend fun getAllWorkoutsByType(type: WorkoutTypes): List<Workout> {
        return workoutDAO.getWorkoutByType(type)
    }

    suspend fun addNewWorkout(workout: Workout) {
        workoutDAO.addWorkout(workout = workout)
        refresh()
    }

    suspend fun deleteWorkout(workout: Workout) {
        workoutDAO.deleteWorkout(workout = workout)
        refresh()
    }

    suspend fun updateWorkout(workout: Workout) {
        workoutDAO.updateWorkout(workout)
        refresh()
    }

    private suspend fun refresh() {
        allWorkouts = null
        getAllWorkouts()
    }
}