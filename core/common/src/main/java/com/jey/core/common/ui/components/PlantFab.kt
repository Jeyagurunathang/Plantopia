package com.jey.core.common.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
        modifier = modifier.size(60.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription
        )
    }
}