package com.galaxy.myplants.plants.domain.use_case

data class PlantUseCases(
    val getPlantsUseCase: GetPlantsUseCase,
    val deletePlantUseCase: DeletePlantUseCase,
    val addPlantUseCase: AddPlantUseCase,
    val getPlantUseCase: GetPlantUseCase
)