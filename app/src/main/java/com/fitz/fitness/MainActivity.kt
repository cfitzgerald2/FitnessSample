package com.fitz.fitness

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitz.fitness.repository.Workout
import com.fitz.fitness.ui.theme.FitnessTheme
import com.fitz.fitness.util.DateFormatter.format
import com.fitz.fitness.viewmodel.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WorkoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    {
                        selectedWorkout.value = it
                        showDialogState.value = true
                    } ,
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
}

@Composable
fun Page(workouts: MutableState<List<Workout>>, cellOnClick: (Workout) -> Unit, fabOnclick: () -> Unit) {
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
                    WorkoutCell(workout = workouts.value[workout], onClick = cellOnClick)
                }
            }
        }
    }
}

@Composable
fun WorkoutList(workouts: MutableState<List<Workout>>, cellClick: (Workout) -> Unit) {
    Column() {
        workouts.value.forEach{
            WorkoutCell(workout = it, onClick = cellClick)
        }
    }
}

@Composable
fun WorkoutCell(workout: Workout, onClick: (Workout) -> Unit) {
    Card(modifier = Modifier.clickable(enabled = true, onClick = {onClick(workout)})) {
        Row() {
            Text(workout.workoutName.displayName)
            Text(" on " + workout.date.format())
            Badge(workout.repititions.toString())
        }
    }
}


@Composable
fun Badge(text: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.sizeIn(minHeight = 36.dp)){
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_star_border_24),
            "Badge background",
            modifier = Modifier.sizeIn(minWidth = 32.dp, minHeight = 32.dp)
        )
        Text(text = text, modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.contentColorFor(MaterialTheme.colors.surface),
        fontSize = 24.sp
    )
}

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
        Page(state, {}, {})
    }
}