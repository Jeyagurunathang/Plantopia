package com.jey.core.common.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlantTextField(
    modifier: Modifier = Modifier,
    label: String,
    maximumLines: Int = 1,
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(20.dp),
        placeholder = { Text(label) },
        maxLines = maximumLines,
        modifier = modifier.border(
            width = 1.dp,
            color = Color.Gray,
            shape = RoundedCornerShape(20.dp)
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PlantTextFieldPreview() {
    PlantTextField(
        label = "Plant Name",
        value = "",
        onValueChange = {}
    )
}