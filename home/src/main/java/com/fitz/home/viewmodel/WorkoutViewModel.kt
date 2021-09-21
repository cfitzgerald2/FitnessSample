package com.fitz.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitz.core.repository.models.Workout
import com.fitz.core.repository.usecases.WorkoutDataAccessor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * view model for the main activity, to hold/request workout info
 */
@HiltViewModel
class WorkoutViewModel @Inject constructor(private val accessor: WorkoutDataAccessor): ViewModel() {

    val allWords: MutableState<List<Workout>> = mutableStateOf(listOf())

    val workoutTransformation: LiveData<Boolean> = Transformations.map(accessor.getObservable()) {
        allWords.value = it
        true
    }

    private suspend fun getAllWorkouts() {
        allWords.value = accessor.getAll()
    }

    fun getAllWorkoutsAsync() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllWorkouts()
        }
    }

    fun addWorkoutAsync(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            accessor.add(workout)
            getAllWorkouts()
        }
    }

    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            accessor.delete(workout)
            getAllWorkouts()
        }
    }

}