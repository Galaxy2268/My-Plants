package com.galaxy.myplants.plants.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.galaxy.myplants.plants.domain.model.Plant


@Database(
    entities = [Plant::class],
    version = 1
)
abstract class PlantDatabase: RoomDatabase() {
    abstract val plantDao: PlantDao
    companion object{
        const val DATABASE_NAME = "plant_db"
    }
}