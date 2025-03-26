package com.galaxy.myplants.plants.data.repository

import com.galaxy.myplants.plants.data.datasource.PlantDao
import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow

class PlantRepositoryImpl(private val dao: PlantDao): PlantRepository {
    override fun getPlants(): Flow<List<Plant>> {
        return dao.getPlants()
    }

    override suspend fun getPlantById(id: Int): Plant? {
        return dao.getPlantById(id)
    }

    override suspend fun insertPlant(plant: Plant) {
        return dao.insertPlant(plant)
    }

    override suspend fun deletePlant(plant: Plant) {
        return dao.deletePlant(plant)
    }
}