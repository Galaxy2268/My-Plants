package com.galaxy.myplants.plants.domain.repository

import com.galaxy.myplants.plants.domain.model.Plant

interface PlantRepository {

    suspend fun getPlants(): List<Plant>

    suspend fun getPlantById(id: Int): Plant?

    suspend fun insertPlant(plant: Plant)

    suspend fun deletePlant(plant: Plant)
}