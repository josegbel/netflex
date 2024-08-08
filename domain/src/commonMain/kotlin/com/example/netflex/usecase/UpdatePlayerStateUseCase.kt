package com.example.netflex.usecase

import com.example.netflex.VideoPlayerController
import com.example.netflex.VideoPlayerState

class UpdatePlayerStateUseCase(
    private val videoPlayerController: VideoPlayerController,
) {
    operator fun invoke(currentState: VideoPlayerState): VideoPlayerState =
        currentState.copy(
            currentPosition = videoPlayerController.getCurrentPosition(),
            duration = videoPlayerController.getDuration(),
            volume = videoPlayerController.getVolume(),
            isPlaying = videoPlayerController.isPlaying(),
        )
}
