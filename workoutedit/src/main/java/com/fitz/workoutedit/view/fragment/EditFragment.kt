package com.fitz.workoutedit.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fitz.core.WorkoutTypes
import com.fitz.core.viewutils.DateFormatter.format
import com.fitz.workoutedit.R
import com.fitz.workoutedit.ViewState
import com.fitz.workoutedit.models.EditWorkoutViewConfig
import com.fitz.workoutedit.models.WorkoutBuilder
import com.fitz.workoutedit.viewmodel.EditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment : Fragment(R.layout.fragment_edit) {

    //todo inject
    lateinit var workoutConfig: EditWorkoutViewConfig

    private val viewModel: EditViewModel by viewModels()

    private lateinit var repetitionsEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var workoutTypeEditText: AutoCompleteTextView
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repetitionsEditText = view.findViewById(R.id.repetitions_edittext)
        dateEditText = view.findViewById(R.id.workout_date_edittext)
        workoutTypeEditText = view.findViewById(R.id.workout_type_dropdown)
        saveButton = view.findViewById(R.id.save_button)
        cancelButton = view.findViewById(R.id.cancel_button)
        progressBar = view.findViewById(R.id.progress_bar)

        workoutConfig.workout?.let {
            repetitionsEditText.setText(it.repetitions.toString())
            dateEditText.setText(it.date.format())
            workoutTypeEditText.setText(it.workoutName.displayName)
        }
        workoutTypeEditText.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                WorkoutTypes.values().map { it.displayName }
            )
        )
        saveButton.text = workoutConfig.saveText
        saveButton.setOnClickListener { save() }
        cancelButton.setOnClickListener { endFlow() }


        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                ViewState.SUCCESS -> endFlow()
                ViewState.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "There was a problem saving this data. Please review", Toast.LENGTH_SHORT).show()
                }
                ViewState.LOADING -> progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun getData(): WorkoutBuilder {
        return WorkoutBuilder(
            dateEditText.text?.toString(),
            workoutTypeEditText.text?.toString(),
            repetitionsEditText.text?.toString(),
            workoutConfig.workout?.id
        )
    }

    private fun save() {
        viewModel.updateDataAsync(getData())
    }

    private fun endFlow() = activity?.finish()

}