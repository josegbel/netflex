package com.example.netflex.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import com.example.netflex.VideoPlayerController
import com.example.netflex.video.IosVideoPlayerController
import com.example.netflex.viewmodel.VideoPlayerViewModel
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.play

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayerView(
    modifier: Modifier,
    viewModel: VideoPlayerViewModel,
    videoPlayerController: VideoPlayerController,
) {
    val vpc = remember { videoPlayerController as IosVideoPlayerController }
    val state = viewModel.videoState.collectAsState()

    LaunchedEffect(state.value.isPlaying) {
        if (state.value.isPlaying) {
            vpc.play()
        } else {
            vpc.pause()
        }
    }

    val factory =
        remember(vpc) {
            { vpc.getPlayerView() }
        }

    UIKitView(
        factory = factory,
        modifier = modifier,
    )
}
