package com.galaxy.myplants.plants.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.galaxy.myplants.plants.domain.model.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant")
    fun getPlants(): Flow<List<Plant>>

    @Query("SELECT * FROM plant WHERE id = :id")
    suspend fun getPlantById(id: Int): Plant?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(note: Plant)

    @Delete
    suspend fun deletePlant(note: Plant)
}