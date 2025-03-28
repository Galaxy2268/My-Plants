package com.galaxy.myplants.plants.presentation.util

import android.content.Context
import android.net.Uri
import java.io.File

fun deleteFileForImage(context: Context, imageUri: Uri){
    val file = File(context.filesDir, imageUri.lastPathSegment ?: return)
    file.delete()
}