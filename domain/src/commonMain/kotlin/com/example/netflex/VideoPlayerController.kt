package com.example.netflex

interface VideoPlayerController {
    fun play()

    fun pause()

    fun seekTo(position: Long)

    fun setVolume(volume: Float)

    fun getVolume(): Float

    fun getCurrentPosition(): Long

    fun getDuration(): Long

    fun isPlaying(): Boolean
}
