package com.fitz.core.repository.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import com.fitz.core.WorkoutTypes
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "workout")
data class Workout(
    @ColumnInfo(name = "date") var date: Date,
    @ColumnInfo(name = "workout_name") var workoutName: WorkoutTypes,
    @ColumnInfo(name = "count") var repetitions: Int,
    @PrimaryKey(autoGenerate = true) var  id: Int? = null
) : Parcelable
