package com.fitz.workoutedit.models

import com.fitz.core.repository.models.Workout

data class EditWorkoutViewConfig(
    val workout: Workout?,
    val saveText: String
)
