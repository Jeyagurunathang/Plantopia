package com.jey.core.model

data class Plant (
    val id: Int,
    val name: String,
    val description: String,
    val image: String? = null,
)