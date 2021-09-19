package com.fitz.core.repository.converters

import androidx.room.TypeConverter
import com.fitz.core.WorkoutTypes
import java.util.*

/**
 * convert data when reading from or writing to the database
 */

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