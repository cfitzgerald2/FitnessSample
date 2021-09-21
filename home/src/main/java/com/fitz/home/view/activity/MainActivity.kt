package com.fitz.home.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fitz.core.WorkoutTypes
import com.fitz.core.repository.models.Workout
import com.fitz.home.R
import com.fitz.home.theme.FitnessTheme
import com.fitz.home.view.composables.WorkoutCell
import com.fitz.home.view.composables.text.Title
import com.fitz.home.viewmodel.WorkoutViewModel
import com.fitz.usecases.navigation.AppNavigation
import com.fitz.usecases.navigation.AppNavigation.Companion.WORKOUT_ARGUMENT_KEY
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * entry page of the app
 */

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WorkoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // observe to verify the transformation is carried out
        viewModel.workoutTransformation.observe(this) {}

        setContent {
            FitnessTheme {
                // A surface container using the 'background' color from the theme
                val showDialogState = remember {
                    mutableStateOf(false)
                }
                val selectedWorkout: MutableState<Workout?> = remember {
                    mutableStateOf(null)
                }
                Page(
                    viewModel.allWords,
                    { workout ->
                        selectedWorkout.value = workout
                        showDialogState.value = true
                    } ,
                    { workout ->
                        launchEditFlow(workout)
                    },
                    {
                        viewModel.addWorkoutAsync(Workout(Date(), WorkoutTypes.CRUNCHES, 20))
                    }
                )
                if(showDialogState.value && selectedWorkout.value != null) {
                    MyAlertDialog(
                        showDialogState = showDialogState,
                        selectedWorkout = selectedWorkout
                    )
                }
            }
        }
        viewModel.getAllWorkoutsAsync()
    }

    @Composable
    fun MyAlertDialog(showDialogState: MutableState<Boolean>, selectedWorkout: MutableState<Workout?>) {
        AlertDialog(
            onDismissRequest = {
                showDialogState.value = false
                selectedWorkout.value = null
                Log.d("TAG_ME", "MyAlertDialog: onDismissRequest")
            },
            title = {
                Text(text = "Are you sure you want to delete this workout?")
            },
            text = {
                Text(text = "Deleting this entry is final and cannot be reverted.")
            },
            buttons = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, end = 16.dp, start = 16.dp), horizontalArrangement = Arrangement.End) {
                    Text(
                        "Cancel",
                        modifier = Modifier
                            .clickable {
                                showDialogState.value = false
                            }
                            .padding(horizontal = 16.dp),
                        color = Color.Gray
                    )
                    Text(
                        "Delete",
                        modifier = Modifier
                            .clickable {
                                viewModel.deleteWorkout(selectedWorkout.value!!)
                                showDialogState.value = false
                            }
                            .padding(horizontal = 16.dp),
                        color = Color.Red
                    )
                }
            }
        )
    }

    private fun launchEditFlow(workout: Workout) {
        val intent = Intent(this, (application as AppNavigation).getEditActivity())
        intent.putExtra(WORKOUT_ARGUMENT_KEY, workout)
        this.startActivity(intent)
    }
}

@ExperimentalFoundationApi
@Composable
fun Page(workouts: MutableState<List<Workout>>, cellOnClick: (Workout) -> Unit, cellOnLongClick: (Workout) -> Unit, fabOnclick: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(floatingActionButton = {
            FloatingActionButton(onClick = {
                fabOnclick()
            }
            ){
                Icon(painter = painterResource(id = R.drawable.ic_baseline_add), contentDescription = "Add workout button.")
            }
        }){
            val scrollableState = remember {
                ScrollableState{
                    it
                }
            }
            LazyColumn(Modifier.scrollable(orientation = Orientation.Vertical, state = scrollableState)) {
                item{
                    Title(text = "Fitness")
                }
                this.items(workouts.value.size) { workout ->
                    WorkoutCell(workout = workouts.value[workout], onClick = cellOnClick, onLongClick = cellOnLongClick)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val state: MutableState<List<Workout>> = remember {
        mutableStateOf(
            listOf(
                Workout(Date(), WorkoutTypes.PUSH_UPS, 5)
            )
        )
    }
    FitnessTheme {
        Page(state, {}, {}, {})
    }
}