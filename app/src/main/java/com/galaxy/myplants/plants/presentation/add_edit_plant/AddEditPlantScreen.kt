package com.galaxy.myplants.plants.presentation.add_edit_plant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.galaxy.myplants.R
import com.galaxy.myplants.plants.presentation.components.FloatingButton
import com.galaxy.myplants.plants.presentation.util.Screens
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditPlantScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEditPlantViewModel = hiltViewModel(),
    navController: NavController,
    snackBarHostState: SnackbarHostState

) {
    val image = viewModel.image.value
    val name = viewModel.plantName.value
    val neededWater = viewModel.neededWater.value
    val daysToWater = viewModel.daysToWater.value
    val painter = if (image.isNotBlank()) {
        rememberAsyncImagePainter(image)
    } else {
        painterResource(id = R.drawable.plant_placeholder)
    }


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is AddEditPlantViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
                is AddEditPlantViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))
            Image(
                painter = painter,
                contentDescription = "Plant Image",
                modifier = Modifier
                    .size(240.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {},
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Take picture of your plant!")
            }
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = name,
                onValueChange = viewModel::onNameChange,
                placeholder = { Text(text = "Give name to it") },
                label = { Text("Name") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = daysToWater,
                onValueChange = viewModel::onDaysToWaterChange,
                placeholder = { Text(text = "How often it needs water?") },
                label = { Text("days") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.NumberPassword
                ),
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = neededWater,
                onValueChange = viewModel::onNeededWaterChange,
                placeholder = { Text(text = "How much water it needs?") },
                label = { Text("needed water ml") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.NumberPassword
                ),
            )
        }
        FloatingButton(
            onClick = {
                viewModel.addPlant()
            },
            icon = Icons.Filled.Save,
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd)
        )
    }


}