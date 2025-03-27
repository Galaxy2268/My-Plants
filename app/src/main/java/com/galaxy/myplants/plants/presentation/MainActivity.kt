package com.galaxy.myplants.plants.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galaxy.myplants.plants.presentation.add_edit_plant.AddEditPlantScreen
import com.galaxy.myplants.plants.presentation.plants.PlantsScreen
import com.galaxy.myplants.plants.presentation.util.Screens
import com.galaxy.myplants.ui.theme.MyPlantsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPlantsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.PlantsScreen
                    ){
                        composable<Screens.PlantsScreen>{
                            PlantsScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable<Screens.PlantScreen> {
                            AddEditPlantScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding),
                            )
                        }
                    }

                }
            }
        }
    }
}

