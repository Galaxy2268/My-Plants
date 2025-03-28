package com.galaxy.myplants.plants.presentation.plants


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.galaxy.myplants.plants.presentation.components.FloatingButton
import com.galaxy.myplants.plants.presentation.components.PlantCard
import com.galaxy.myplants.plants.presentation.util.Screens
import com.galaxy.myplants.plants.presentation.util.deleteFileForImage

@Composable
fun PlantsScreen(
    modifier: Modifier = Modifier,
    viewModel: PlantsViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxSize()
        ) {
            items(viewModel.plants.value) { plant ->
                PlantCard(
                    plant = plant,
                    onClick = {
                        navController.navigate(Screens.PlantScreen(id = plant.id))
                    },
                    onDelete = {
                        deleteFileForImage(context, plant.image.toUri())
                        viewModel.deletePlant(plant)
                    }
                )
            }
        }
        FloatingButton(
            onClick = {
                navController.navigate(Screens.PlantScreen(id = 0))
            },
            icon = Icons.Filled.Add,
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd)
        )
    }

}

