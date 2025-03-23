package com.galaxy.myplants.plants.domain.use_case

import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.domain.repository.PlantRepository

class GetPlantsUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(): List<Plant>{
        return repository.getPlants()
    }
}