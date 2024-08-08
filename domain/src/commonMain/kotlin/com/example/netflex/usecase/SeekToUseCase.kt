package com.example.netflex.usecase

import com.example.netflex.VideoPlayerController
import com.example.netflex.VideoPlayerState

class SeekToUseCase(
    private val videoPlayerController: VideoPlayerController,
) {
    operator fun invoke(position: Long): VideoPlayerState {
        videoPlayerController.seekTo(position)

        return VideoPlayerState(
            isPlaying = videoPlayerController.isPlaying(),
            currentPosition = videoPlayerController.getCurrentPosition(),
            duration = videoPlayerController.getDuration(),
            volume = videoPlayerController.getVolume(),
        )
    }
}
