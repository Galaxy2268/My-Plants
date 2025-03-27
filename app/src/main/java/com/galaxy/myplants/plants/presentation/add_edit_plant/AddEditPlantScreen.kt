package com.galaxy.myplants.plants.presentation.add_edit_plant

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.galaxy.myplants.R
import com.galaxy.myplants.plants.presentation.components.FloatingButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditPlantScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEditPlantViewModel = hiltViewModel(),
    navController: NavController,

) {
    val maxCharName = 20
    val maxCharWater = 5
    val maxCharDays = 2
    val image = viewModel.image.value
    val name = viewModel.plantName.value
    val neededWater = viewModel.neededWater.value
    val daysToWater = viewModel.daysToWater.value
    val context = LocalContext.current
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
                is AddEditPlantViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
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
                onValueChange = {
                    if(it.length <= maxCharName) viewModel.onNameChange(it)
                },
                placeholder = { Text(text = "Give name to it") },
                label = { Text("Name") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = daysToWater,
                onValueChange = {
                    if(it.length <= maxCharDays) viewModel.onDaysToWaterChange(it)
                },
                placeholder = { Text(text = "How often it needs water?") },
                label = { Text("days till watering") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.NumberPassword
                ),
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = neededWater,
                onValueChange = {
                    if(it.length <= maxCharWater) viewModel.onNeededWaterChange(it)
                },
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