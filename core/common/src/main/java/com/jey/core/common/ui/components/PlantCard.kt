package com.jey.core.common.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jey.core.common.R

@Composable
fun PlantCard(
    modifier: Modifier = Modifier,
    @DrawableRes plantImage: Int,
    plantName: String,
    plantDescription: String? = null
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = plantImage),
                contentDescription = "Plant image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = plantName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.5.sp
            )

            plantDescription?.let {
                Text(text = plantDescription)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantCardPreview() {
    PlantCard(
        plantImage = R.drawable.aloe_vera,
        plantName = "Aloe Vera"
    )
}