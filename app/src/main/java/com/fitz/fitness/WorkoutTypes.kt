package com.fitz.fitness

import java.lang.IllegalArgumentException

enum class WorkoutTypes(val displayName: String) {
    PUSH_UPS("Push-ups"),
    SIT_UPS("Sit-ups"),
    PLANKS("Push-ups"),
    CHIN_UPS("Chin-ups"),
    CRUNCHES("Crunches"),
    UNKNOWN("");

    companion object {
        fun getValueOf(name: String?) : WorkoutTypes? {
            return name?.let {
                try {
                    valueOf(it)
                } catch (e: IllegalArgumentException) {
                    UNKNOWN
                }
            }
        }
    }

}