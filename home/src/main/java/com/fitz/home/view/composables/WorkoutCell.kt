package com.fitz.home.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fitz.core.repository.models.Workout
import com.fitz.home.util.DateFormatter.format

@Composable
fun WorkoutCell(workout: Workout, onClick: (Workout) -> Unit) {
    Card(modifier = Modifier.clickable(enabled = true, onClick = {onClick(workout)})) {
        Row() {
            Text(workout.workoutName.displayName)
            Text(" on " + workout.date.format())
            Badge(workout.repititions.toString())
        }
    }
}