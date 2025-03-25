package com.galaxy.myplants.plants.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plant(
    val name: String,
    val image: String,
    val daysToWater: Int?,
    val neededWater: Int?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

class InvalidPlantException(message: String) : Exception(message)
