package com.galaxy.myplants.plants.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plant(
    val name: String,
    val picture: String,
    val daysToWater: Int?,
    val neededWater: Int?,
    @PrimaryKey val id: Int? = null
)

class InvalidPlantException(message: String) : Exception(message)
