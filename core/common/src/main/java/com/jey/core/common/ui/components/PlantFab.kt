package com.jey.core.common.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.jey.core.common.R

@Composable
fun PlantFab(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int = R.drawable.potted_plant,
    contentDescription: String,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = Color(0xffbcece7)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription
        )
    }
}