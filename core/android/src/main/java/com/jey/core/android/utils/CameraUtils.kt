package com.jey.core.android.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.util.UUID
import javax.inject.Inject

class CameraUtils @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getUriFromFile(): Uri {
        val plantopiaDir = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "plantopia")

        if (!plantopiaDir.exists()) {
            plantopiaDir.mkdir()
        }

        val imageFile = File.createTempFile(
            "${UUID.randomUUID()}_",
            ".jpg",
            plantopiaDir
        )

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileProvider",
            imageFile
        )
    }

    fun deleteImageUri(uri: Uri?) {
        uri?.let {
            context.contentResolver.delete(uri, null, null)
        }
    }
}