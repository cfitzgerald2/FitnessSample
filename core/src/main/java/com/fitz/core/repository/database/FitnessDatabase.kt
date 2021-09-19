package com.fitz.core.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fitz.core.repository.converters.Converters
import com.fitz.core.repository.models.Workout
import com.fitz.core.repository.dao.WorkoutDAO

@Database(entities = [Workout::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converters::class])
abstract class FitnessDatabase: RoomDatabase() {

    abstract fun workoutDAO(): WorkoutDAO

    companion object {

        @Volatile
        var INSTANCE: FitnessDatabase? = null

        fun getDatabase(context: Context): FitnessDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    FitnessDatabase::class.java,
                    "workout_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}