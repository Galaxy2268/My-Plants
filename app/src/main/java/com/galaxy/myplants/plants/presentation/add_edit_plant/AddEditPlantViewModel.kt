package com.galaxy.myplants.plants.presentation.add_edit_plant

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.FileProvider
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galaxy.myplants.plants.domain.model.InvalidPlantException
import com.galaxy.myplants.plants.domain.model.Plant
import com.galaxy.myplants.plants.domain.use_case.PlantUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AddEditPlantViewModel@Inject constructor(
    private val plantUseCases: PlantUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _plantName = mutableStateOf("")
    val plantName: State<String> = _plantName

    private val _image = mutableStateOf("")
    val image: State<String> = _image

    private val _daysToWater = mutableStateOf("")
    val daysToWater: State<String> = _daysToWater

    private val _neededWater = mutableStateOf("")
    val neededWater: State<String> = _neededWater

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPlantId: Int = 0

    private var currentImageUri = Uri.EMPTY

    init {
        savedStateHandle.get<Int>("id")?.let {id ->
            if(id != 0){
                viewModelScope.launch {
                    plantUseCases.getPlantUseCase(id)?.also {plant ->
                        currentPlantId = plant.id
                        _plantName.value = plant.name
                        _image.value = plant.image
                        _daysToWater.value = plant.daysToWater.toString()
                        _neededWater.value = plant.neededWater.toString()
                    }
                }
            }
        }

    }

     fun addPlant(){
        viewModelScope.launch {
            try {
                plantUseCases.addPlantUseCase(
                    Plant(
                        name = plantName.value,
                        image = image.value,
                        daysToWater = daysToWater.value.toInt(),
                        neededWater = neededWater.value.toInt(),
                        id = currentPlantId
                    )
                )
                _eventFlow.emit(UiEvent.SaveNote)
            }catch (e: InvalidPlantException){
                _eventFlow.emit(UiEvent.ShowToast(e.message ?: "Unknown error"))
            }catch (e: NumberFormatException){
                _eventFlow.emit(UiEvent.ShowToast("Days, and water values must be numbers"))
            }
        }
    }

    fun onNameChange(text: String){
        _plantName.value = text
    }

    fun onNeededWaterChange(text: String){
        _neededWater.value = text
    }

    fun onDaysToWaterChange(text: String){
        _daysToWater.value = text
    }

    fun updateImage() {
        _image.value = currentImageUri.toString()
    }

    fun createFileForImage(context: Context): Uri{
        val file = File(context.filesDir, "image_${System.currentTimeMillis()}.jpg")
        val uri = FileProvider.getUriForFile(context,"com.galaxy.myplants.provider", file)
        currentImageUri = uri
        return uri
    }



    sealed class UiEvent{
        data class ShowToast(val message: String): UiEvent()
        data object SaveNote: UiEvent()
    }

}