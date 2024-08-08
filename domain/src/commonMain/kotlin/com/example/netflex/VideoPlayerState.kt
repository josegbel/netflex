package com.example.netflex

data class VideoPlayerState(
    val isPlaying: Boolean = true,
    val volume: Float = 1.0f,
    val currentPosition: Long = 0,
    val duration: Long = 0,
)
