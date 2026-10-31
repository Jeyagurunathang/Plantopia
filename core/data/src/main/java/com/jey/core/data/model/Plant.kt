package com.jey.core.data.model

import com.jey.core.database.entity.PlantEntity
import com.jey.core.model.Plant

fun Plant.asEntity(): PlantEntity = PlantEntity(
    plantName = this.name,
    description = this.description,
    plantImagePath = this.image
)
