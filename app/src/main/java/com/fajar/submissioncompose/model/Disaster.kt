package com.fajar.submissioncompose.model

data class Disaster(
    val id: Long,
    val name: String,
    val date: String,
    val location: String,
    val description: String,
    val deathToll: Int,
    val image: Int
)
