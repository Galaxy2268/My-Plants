package com.galaxy.myplants.plants.presentation.plants


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.presentation.components.FloatingButton
import com.galaxy.myplants.plants.presentation.components.PlantCard

@Composable
fun PlantsScreen(
    modifier: Modifier = Modifier,
    viewModel: PlantsViewModel = hiltViewModel(),
    navController: NavController
){
    Column(
        modifier = modifier.fillMaxSize()
    ){
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxSize()
            ){
                items(viewModel.plants.value){plant ->
                    PlantCard(
                        plant
                    )
                }
            }
            FloatingButton(
                onClick = {},
                icon = Icons.Filled.Add,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

