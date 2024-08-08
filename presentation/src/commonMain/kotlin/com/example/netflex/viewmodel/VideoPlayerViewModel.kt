package com.example.netflex.viewmodel

import com.example.netflex.VideoPlayerState
import com.example.netflex.usecase.PlayPauseUseCase
import com.example.netflex.usecase.SeekToUseCase
import com.example.netflex.usecase.SetVolumeUseCase
import com.example.netflex.usecase.UpdatePlayerStateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VideoPlayerViewModel(
    private val playPauseUseCase: PlayPauseUseCase,
    private val seekToUseCase: SeekToUseCase,
    private val setVolumeUseCase: SetVolumeUseCase,
    private val updatePlayerStateUseCase: UpdatePlayerStateUseCase,
) : CoroutineScope by MainScope() {
    private val _videoState = MutableStateFlow(VideoPlayerState())
    val videoState: StateFlow<VideoPlayerState> = _videoState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    init {
        startUpdatingPlayerState()
    }

    fun onPlayPauseClicked() {
        viewModelScope.launch {
            _videoState.value = playPauseUseCase(!videoState.value.isPlaying)
        }
    }

    fun seekTo(position: Long) {
        viewModelScope.launch {
            _videoState.value = seekToUseCase(position)
        }
    }

    fun setVolume(volume: Float) {
        viewModelScope.launch {
            _videoState.value = setVolumeUseCase(volume)
        }
    }

    private fun startUpdatingPlayerState() {
        launch {
            while (true) {
                _videoState.value = updatePlayerStateUseCase(_videoState.value)
                delay(250)
            }
        }
    }
}
