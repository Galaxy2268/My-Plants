package com.galaxy.myplants.plants.domain.use_case

import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.domain.repository.PlantRepository

class DeletePlantUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(plant: Plant){
        repository.deletePlant(plant)
    }
}