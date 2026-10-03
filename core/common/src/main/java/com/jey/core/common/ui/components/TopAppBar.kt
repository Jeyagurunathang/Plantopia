package com.jey.core.common.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jey.core.common.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "Plantopia") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.menu),
                    contentDescription = "Menu"
                )
            }
        }
    )
}