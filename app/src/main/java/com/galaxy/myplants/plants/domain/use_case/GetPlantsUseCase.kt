package com.galaxy.myplants.plants.domain.use_case

import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow

class GetPlantsUseCase(private val repository: PlantRepository) {
    operator fun invoke(): Flow<List<Plant>> {
        return repository.getPlants()
    }
}