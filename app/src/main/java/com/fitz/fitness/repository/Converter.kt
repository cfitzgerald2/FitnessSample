package com.fitz.fitness.repository

import androidx.room.TypeConverter
import com.fitz.fitness.WorkoutTypes
import java.util.*

class Converters {

    @TypeConverter
    fun fromDateToLong(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromLongToDate(time: Long?): Date? {
        return time?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromWorkoutTypeToString(type: WorkoutTypes?): String? {
        return type?.name
    }

    @TypeConverter
    fun fromStringToWorkoutType(name: String?): WorkoutTypes? {
        return WorkoutTypes.getValueOf(name)
    }
}