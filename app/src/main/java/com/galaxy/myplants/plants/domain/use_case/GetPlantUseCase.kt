package com.galaxy.myplants.plants.domain.use_case

import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.domain.repository.PlantRepository

class GetPlantUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(id: Int): Plant?{
        return repository.getPlantById(id)
    }
}