package com.galaxy.myplants.plants.domain.use_case

import com.galaxy.myplants.plants.domain.model.InvalidPlantException
import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.domain.repository.PlantRepository

class AddPlantUseCase(private val repository: PlantRepository) {
    @Throws(InvalidPlantException::class)
    suspend operator fun invoke(plant: Plant){
        if (plant.name.isBlank()){
            throw InvalidPlantException("The name can't be empty")
        }
        if (plant.daysToWater == null){
            throw InvalidPlantException("The daysToWater can't be empty")
        }
        if (plant.neededWater == null){
            throw InvalidPlantException("The neededWater can't be empty")
        }
        repository.insertPlant(plant)
    }
}