package com.fitz.workoutedit.models

import com.fitz.core.WorkoutTypes
import com.fitz.core.repository.models.Workout
import com.fitz.core.viewutils.DateFormatter.formatToDate
import java.lang.IllegalArgumentException
import java.text.ParseException

data class WorkoutBuilder(
    var date: String? = null,
    var workoutName: String? = null,
    var repetitions: String? = null,
    val id: Int? = null
) {

    @Throws(IllegalArgumentException::class)
    fun build(): Workout {
        val mDate = date
        val mWorkoutName = workoutName
        val mRepetitions = repetitions
        if(mDate == null || mWorkoutName == null || mRepetitions == null) {
            throw IllegalArgumentException("date: $mDate, workoutName: $mWorkoutName, and repetitions: $mRepetitions must all be non-null")
        } else {
            val formattedDate = try {
                mDate.formatToDate()
            } catch (e: ParseException) {
                throw IllegalArgumentException("date: $mDate is improperly formatted", e)
            }
            val workoutName = WorkoutTypes.getValueOf(mWorkoutName) ?: WorkoutTypes.UNKNOWN

            val repetitions = try {
                mRepetitions.toInt()
            } catch(e: NumberFormatException) {
                throw IllegalArgumentException("repetitions: $mRepetitions is a bad number", e)
            }
            val workout = Workout(formattedDate, workoutName, repetitions)
            workout.id = id
            return workout
        }
    }
}