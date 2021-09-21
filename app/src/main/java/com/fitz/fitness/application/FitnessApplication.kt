package com.fitz.fitness.application

import android.app.Activity
import android.app.Application
import com.fitz.usecases.navigation.AppNavigation
import com.fitz.workoutedit.view.activity.EditActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitnessApplication: Application(), AppNavigation {
    override fun getEditActivity(): Class<*> {
        return EditActivity::class.java
    }
}