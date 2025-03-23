package com.galaxy.myplants.di

import android.app.Application
import androidx.room.Room
import com.galaxy.myplants.plants.data.datasource.PlantDatabase
import com.galaxy.myplants.plants.data.repository.PlantRepositoryImpl
import com.galaxy.myplants.plants.domain.repository.PlantRepository
import com.galaxy.myplants.plants.domain.use_case.AddPlantUseCase
import com.galaxy.myplants.plants.domain.use_case.DeletePlantUseCase
import com.galaxy.myplants.plants.domain.use_case.GetPlantUseCase
import com.galaxy.myplants.plants.domain.use_case.GetPlantsUseCase
import com.galaxy.myplants.plants.domain.use_case.PlantUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePlantDataBase(app: Application): PlantDatabase{
        return Room.databaseBuilder(
            app,
            PlantDatabase::class.java,
            PlantDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: PlantDatabase): PlantRepository{
        return PlantRepositoryImpl(db.plantDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: PlantRepository): PlantUseCases {
        return PlantUseCases(
            getPlantsUseCase = GetPlantsUseCase(repository),
            deletePlantUseCase = DeletePlantUseCase(repository),
            addPlantUseCase = AddPlantUseCase(repository),
            getPlantUseCase = GetPlantUseCase(repository)
        )
    }
}