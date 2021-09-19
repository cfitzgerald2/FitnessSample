package com.fitz.core.repository.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fitz.core.WorkoutTypes
import java.util.*

@Entity(tableName = "workout")
data class Workout(
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "workout_name") val workoutName: WorkoutTypes,
    @ColumnInfo(name = "count") val repititions: Int
) {
    @PrimaryKey(autoGenerate = true) var  id: Int? = null
}
