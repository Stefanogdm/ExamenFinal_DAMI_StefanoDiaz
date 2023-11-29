package com.example.myvideo.VideoRest

data class Videos(
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
)

