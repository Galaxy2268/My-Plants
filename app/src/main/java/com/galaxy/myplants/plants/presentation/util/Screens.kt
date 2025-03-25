package com.galaxy.myplants.plants.presentation.util

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object PlantsScreen: Screens()
    @Serializable
    data class PlantScreen(
        val id: Int,
    ): Screens()
}