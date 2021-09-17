package com.fitz.fitness.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitz.fitness.repository.Workout
import com.fitz.fitness.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(val repository: WorkoutRepository): ViewModel() {

    val allWords: MutableState<List<Workout>> = mutableStateOf(listOf())

    private suspend fun getAllWorkouts() {
        allWords.value = repository.getAllWorkouts()
    }

    fun getAllWorkoutsAsync() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllWorkouts()
        }
    }

    fun addWorkoutAsync(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewWorkout(workout)
            getAllWorkouts()
        }
    }

    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWorkout(workout)
            getAllWorkouts()
        }
    }

}