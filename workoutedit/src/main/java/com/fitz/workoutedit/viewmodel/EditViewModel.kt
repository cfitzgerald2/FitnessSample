package com.fitz.workoutedit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitz.core.repository.models.Workout
import com.fitz.core.repository.usecases.WorkoutDataAccessor
import com.fitz.workoutedit.ViewState
import com.fitz.workoutedit.models.WorkoutBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(var workoutUseCase: WorkoutDataAccessor): ViewModel() {

    private var _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = _state

    fun updateDataAsync(workout: WorkoutBuilder) {
        verifyData(workout)?.let {
            _state.postValue(ViewState.LOADING)
            viewModelScope.launch(Dispatchers.IO) {
                updateData(it)
                _state.postValue(ViewState.SUCCESS)
            }
        } ?: _state.postValue(ViewState.ERROR)
    }

    private suspend fun updateData(workout: Workout) = workoutUseCase.update(workout)


    private fun verifyData(workout: WorkoutBuilder): Workout? {
        return try {
            workout.build()
        } catch (e: IllegalArgumentException) {
            Log.d("TAG_ME", "verifyData failed for: $workout with cause: ${e.message}")
            null
        }
    }
}