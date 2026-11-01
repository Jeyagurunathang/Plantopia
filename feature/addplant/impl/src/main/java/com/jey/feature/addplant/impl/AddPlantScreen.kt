package com.jey.feature.addplant.impl

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jey.core.android.utils.CameraUtils
import com.jey.core.common.ui.components.PlantTextField

@Composable
fun AddPlantScreen(
    modifier: Modifier = Modifier,
    viewModel: AddPlantScreenViewModel,
    onBack: () -> Unit,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<String?>(null) }
    var bottonSheetExpanded by remember { mutableStateOf(false) }

    AddPlantScreenContent(
        modifier = modifier,
        name = name,
        onNameChanges = { name = it },
        description = description,
        onDescriptionChange = { description = it },
        selectedImage = selectedImage,
        onSelectedImageChange = { selectedImage = it },
        onAddClicked = viewModel::insertDataIntoDb,
        isBottonSheetExpanded = bottonSheetExpanded,
        onUpdateBottomSheetExpanded = { bottonSheetExpanded = it },
        onBack = onBack
    )
}

@Composable
fun AddPlantScreenContent(
    modifier: Modifier = Modifier,
    name: String,
    onNameChanges: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    selectedImage: String?,
    onSelectedImageChange: (String) -> Unit,
    onAddClicked: (String, String, String?) -> Unit,
    isBottonSheetExpanded: Boolean,
    onUpdateBottomSheetExpanded: (Boolean) -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    var tempImageUriCapturedFromCamera by remember { mutableStateOf<Uri?>(null) }

    val photoMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            onSelectedImageChange(uri.toString())
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    val cameraLaunch = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            onSelectedImageChange(tempImageUriCapturedFromCamera.toString())
        } else {
            CameraUtils(context).deleteImageUri(tempImageUriCapturedFromCamera)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        onUpdateBottomSheetExpanded(true)
                    }
            ) {
                if (selectedImage != null) {
                    AsyncImage(
                        model = selectedImage,
                        contentDescription = "Selected Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .matchParentSize()
                            .clip(shape = RoundedCornerShape(12.dp))
                    )
                } else {
                    Text(
                        text = "Select an image",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            PlantTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Plant Name",
                value = name,
                onValueChange = onNameChanges
            )

            PlantTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Description",
                value = description,
                onValueChange = onDescriptionChange,
                maximumLines = 5
            )
        }

        Button(
            onClick = {
                onAddClicked(name, description, selectedImage)
                onBack()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(R.drawable.correct),
                contentDescription = "Add Plant"
            )
        }

        PhotoAttachmentBottomSheet(
            isBottomSheetExpanded = isBottonSheetExpanded,
            onDismiss = { onUpdateBottomSheetExpanded(false) },
            launchCamera = {
                val imageUri = CameraUtils(context).getUriFromFile()
                tempImageUriCapturedFromCamera = imageUri

                cameraLaunch.launch(tempImageUriCapturedFromCamera)
            },
            launchPhotoPicker = { photoMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoAttachmentBottomSheet(
    modifier: Modifier = Modifier,
    isBottomSheetExpanded: Boolean,
    onDismiss: () -> Unit,
    launchPhotoPicker: () -> Unit,
    launchCamera: () -> Unit,
) {
    if (isBottomSheetExpanded) {
        ModalBottomSheet(
            modifier = modifier,
            onDismissRequest = onDismiss,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PhotoAttachmentBottomSheetItem(
                    icon = R.drawable.camera,
                    iconDescription = "Camera",
                    label = "Camera",
                    onClick = {
                        launchCamera()
                        onDismiss()
                    },
                )

                PhotoAttachmentBottomSheetItem(
                    icon = R.drawable.photo_library,
                    iconDescription = "Gallery",
                    label = "Gallery",
                    onClick = {
                        launchPhotoPicker()
                        onDismiss()
                    }
                )
            }
        }
    }
}

@Composable
fun PhotoAttachmentBottomSheetItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    iconDescription: String,
    label: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = iconDescription
            )

            Text(text = label)
        }
    }
}