package com.fitz.usecases.navigation

interface AppNavigation {
    fun getEditActivity(): Class<*>

    companion object {
        const val WORKOUT_ARGUMENT_KEY = "workout_arg_key"
    }
}