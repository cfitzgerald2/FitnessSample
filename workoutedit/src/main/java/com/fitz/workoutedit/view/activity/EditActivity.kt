package com.fitz.workoutedit.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.findFragment
import com.fitz.core.repository.models.Workout
import com.fitz.usecases.navigation.AppNavigation.Companion.WORKOUT_ARGUMENT_KEY
import com.fitz.workoutedit.R
import com.fitz.workoutedit.models.EditWorkoutViewConfig
import com.fitz.workoutedit.view.fragment.EditFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val workout = intent.getParcelableExtra<Workout>(WORKOUT_ARGUMENT_KEY)

        // todo bind with dagger
        val fragment = EditFragment()
        fragment.workoutConfig = EditWorkoutViewConfig(
            workout,
            getString(R.string.save_text)
        )
        supportFragmentManager.beginTransaction().replace(R.id.edit_activity_container, fragment).commit()
    }
}