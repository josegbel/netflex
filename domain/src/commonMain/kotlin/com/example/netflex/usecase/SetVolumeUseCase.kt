package com.example.netflex.usecase

import com.example.netflex.VideoPlayerController
import com.example.netflex.VideoPlayerState

class SetVolumeUseCase(
    private val videoPlayerController: VideoPlayerController,
) {
    operator fun invoke(volume: Float): VideoPlayerState {
        videoPlayerController.setVolume(volume)

        return VideoPlayerState(
            isPlaying = videoPlayerController.isPlaying(),
            currentPosition = videoPlayerController.getCurrentPosition(),
            duration = videoPlayerController.getDuration(),
            volume = videoPlayerController.getVolume(),
        )
    }
}
