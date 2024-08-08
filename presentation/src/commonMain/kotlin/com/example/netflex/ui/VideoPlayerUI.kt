package com.example.netflex.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.netflex.VideoPlayerController
import com.example.netflex.VideoPlayerState
import com.example.netflex.viewmodel.VideoPlayerViewModel

@Composable
fun VideoPlayerScreen(
    viewModel: VideoPlayerViewModel,
    videoPlayerController: VideoPlayerController,
) {
    val videoState: State<VideoPlayerState> = viewModel.videoState.collectAsState()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VideoPlayerView(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            viewModel = viewModel,
            videoPlayerController = videoPlayerController,
        )

        Spacer(modifier = Modifier.height(16.dp))

        PlayPauseButton(isPlaying = videoState.value.isPlaying, onClick = { viewModel.onPlayPauseClicked() })

        Spacer(modifier = Modifier.height(16.dp))

        SeekBar(
            progress = videoState.value.currentPosition.toFloat(),
            max = videoState.value.duration.toFloat(),
            onValueChange = { viewModel.seekTo(it.toLong()) },
        )

        Spacer(modifier = Modifier.height(16.dp))

        TimeLabels(
            currentPosition = videoState.value.currentPosition,
            duration = videoState.value.duration,
        )

        Spacer(modifier = Modifier.height(16.dp))

        VolumeBar(
            volume = videoState.value.volume,
            onValueChange = { viewModel.setVolume(it) },
        )
    }
}

@Composable
expect fun VideoPlayerView(
    modifier: Modifier = Modifier,
    viewModel: VideoPlayerViewModel,
    videoPlayerController: VideoPlayerController,
)

@Composable
fun PlayPauseButton(
    isPlaying: Boolean,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        Text(if (isPlaying) "Pause" else "Play")
    }
}

@Composable
fun SeekBar(
    progress: Float,
    max: Float,
    onValueChange: (Float) -> Unit,
) {
    Slider(
        value = progress,
        onValueChange = onValueChange,
        valueRange = 0f..max,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun VolumeBar(
    volume: Float,
    onValueChange: (Float) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Volume")
        Slider(
            value = volume,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun TimeLabels(
    currentPosition: Long,
    duration: Long,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = formatTime(currentPosition))
        Text(text = formatTime(duration))
    }
}

fun formatTime(timeMs: Long): String {
    val totalSeconds = timeMs / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    val displayMinutes = minutes.toString().padStart(2, '0')
    val displaySeconds = seconds.toString().padStart(2, '0')
    return "$displayMinutes:$displaySeconds"
}
