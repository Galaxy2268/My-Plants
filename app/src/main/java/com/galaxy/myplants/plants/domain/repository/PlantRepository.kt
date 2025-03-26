package com.galaxy.myplants.plants.domain.repository

import com.galaxy.myplants.plants.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    fun getPlants(): Flow<List<Plant>>

    suspend fun getPlantById(id: Int): Plant?

    suspend fun insertPlant(plant: Plant)

    suspend fun deletePlant(plant: Plant)
}