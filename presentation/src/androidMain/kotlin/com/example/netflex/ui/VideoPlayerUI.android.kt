package com.example.netflex.ui

import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.netflex.VideoPlayerController
import com.example.netflex.video.AndroidVideoPlayerController
import com.example.netflex.viewmodel.VideoPlayerViewModel

@Composable
actual fun VideoPlayerView(
    modifier: Modifier,
    viewModel: VideoPlayerViewModel,
    videoPlayerController: VideoPlayerController,
) {
    val avpc = videoPlayerController as AndroidVideoPlayerController
    val context = LocalContext.current
    val exoPlayer = remember { avpc.exoPlayer }
    val state = viewModel.videoState.collectAsState()

    DisposableEffect(context) {
        exoPlayer.playWhenReady = true
        onDispose {
            exoPlayer.release()
        }
    }

    LaunchedEffect(state.value.isPlaying) {
        if (state.value.isPlaying) {
            exoPlayer.play()
        } else {
            exoPlayer.pause()
        }
    }

    Box(modifier = modifier) {
        AndroidView(
            factory = {
                SurfaceView(it).apply {
                    clipToOutline = true
                    holder.addCallback(
                        object : SurfaceHolder.Callback {
                            override fun surfaceCreated(holder: SurfaceHolder) {
                                exoPlayer.setVideoSurfaceView(this@apply)
                                exoPlayer.setVideoSurfaceHolder(holder)
                            }

                            override fun surfaceChanged(
                                holder: SurfaceHolder,
                                format: Int,
                                width: Int,
                                height: Int,
                            ) {}

                            override fun surfaceDestroyed(holder: SurfaceHolder) {
                                exoPlayer.setVideoSurfaceHolder(null)
                                exoPlayer.setVideoSurfaceView(null)
                            }
                        },
                    )
                }
            },
        )
    }
}
