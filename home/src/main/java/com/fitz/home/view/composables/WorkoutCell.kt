package com.fitz.home.view.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fitz.core.repository.models.Workout
import com.fitz.core.viewutils.DateFormatter.format

@ExperimentalFoundationApi
@Composable
fun WorkoutCell(workout: Workout, onClick: (Workout) -> Unit, onLongClick: (Workout) -> Unit) {
    Card(
        modifier = Modifier.combinedClickable(
            enabled = true,
            onClick = { onClick(workout) },
            onLongClick = { onLongClick(workout) })
    ) {
        Row() {
            Text(workout.workoutName.displayName)
            Text(" on " + workout.date.format())
            Badge(workout.repetitions.toString())
        }
    }
}