package com.jey.core.android.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID
import javax.inject.Inject

class FileUtils @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun saveToDir(uri: Uri, dispatcher: CoroutineDispatcher): String {
        return withContext(dispatcher) {
            val picturesDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            val plantopiaDir = File(picturesDir, "plantopia")
            if (!plantopiaDir.mkdir() && !plantopiaDir.exists()) {
                Log.e("File", "Plantopia directory is not available")
                error("Plantopia directory is not available")
            }

            val destinationFile = File(plantopiaDir, "${UUID.randomUUID()}.jpg")

            val inputFile = context.contentResolver.openInputStream(uri)

            inputFile?.use { source ->
                destinationFile.outputStream().use { target ->
                    source.copyTo(target)
                }
            }

            destinationFile.absolutePath
        }
    }
}