package com.example.netflex.video

import android.content.Context
import androidx.media3.common.C.TIME_UNSET
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import co.touchlab.kermit.Logger
import com.example.netflex.VideoPlayerController

class AndroidVideoPlayerController(
    context: Context,
    uri: String,
) : VideoPlayerController {
    val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()

    init {
        // Add media item to ExoPlayer
        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    override fun play() {
        Logger.i(VideoPlayerController::class.simpleName!!) { "Playing video" }
        exoPlayer.play()
    }

    override fun pause() {
        Logger.i(VideoPlayerController::class.simpleName!!) { "Pausing video" }
        exoPlayer.pause()
    }

    override fun seekTo(position: Long) {
        Logger.i(VideoPlayerController::class.simpleName!!) { "Seeking to position: $position" }
        exoPlayer.seekTo(position)
    }

    override fun setVolume(volume: Float) {
        Logger.i(VideoPlayerController::class.simpleName!!) { "Setting volume to: $volume" }
        exoPlayer.volume = volume
    }

    override fun getVolume(): Float = exoPlayer.volume

    override fun getCurrentPosition(): Long = exoPlayer.currentPosition

    override fun getDuration(): Long = if (exoPlayer.duration == TIME_UNSET) 0L else exoPlayer.duration

    override fun isPlaying(): Boolean = exoPlayer.isPlaying
}
