package com.fitz.core

import java.lang.IllegalArgumentException

enum class WorkoutTypes(val displayName: String) {
    PUSH_UPS("Push-ups"),
    SIT_UPS("Sit-ups"),
    PLANKS("Push-ups"),
    CHIN_UPS("Chin-ups"),
    CRUNCHES("Crunches"),
    UNKNOWN("Custom");

    companion object {
        fun getValueOf(name: String?) : WorkoutTypes? {
            return name?.let {
                try {
                    values().firstOrNull { enumEntry -> enumEntry.displayName == name } ?: valueOf(it)
                } catch (e: IllegalArgumentException) {
                    UNKNOWN
                }
            }
        }
    }

}