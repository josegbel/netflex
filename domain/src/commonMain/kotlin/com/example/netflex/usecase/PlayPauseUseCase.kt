package com.example.netflex.usecase

import co.touchlab.kermit.Logger
import com.example.netflex.VideoPlayerController
import com.example.netflex.VideoPlayerState

class PlayPauseUseCase(
    private val videoPlayerController: VideoPlayerController,
) {
    operator fun invoke(playIt: Boolean): VideoPlayerState {
        Logger.d(PlayPauseUseCase::class.simpleName!!) { "PlayPauseUseCase invoked with playing=$playIt" }
        println("PlayPauseUseCase invoked with playing=$playIt")
        if (playIt) {
            videoPlayerController.play()
        } else {
            videoPlayerController.pause()
        }

        return VideoPlayerState(
            isPlaying = videoPlayerController.isPlaying(),
            currentPosition = videoPlayerController.getCurrentPosition(),
            duration = videoPlayerController.getDuration(),
            volume = videoPlayerController.getVolume(),
        )
    }
}
