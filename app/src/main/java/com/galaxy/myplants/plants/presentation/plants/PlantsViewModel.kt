package com.galaxy.myplants.plants.presentation.plants

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.domain.use_case.PlantUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantsViewModel @Inject constructor(
    private val plantUseCases: PlantUseCases
): ViewModel() {
    private val _plants = mutableStateOf<List<Plant>>(emptyList())
    val plants: State<List<Plant>> = _plants


    init {
        getPlants()
    }

    private fun getPlants(){
        viewModelScope.launch {
            _plants.value = plantUseCases.getPlantsUseCase()
        }
    }

    public fun deletePlant(plant: Plant){
        viewModelScope.launch {
            plantUseCases.deletePlantUseCase(plant)
        }
    }


}