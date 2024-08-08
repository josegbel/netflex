package com.example.netflex

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import com.example.netflex.ui.VideoPlayerScreen
import com.example.netflex.usecase.PlayPauseUseCase
import com.example.netflex.usecase.SeekToUseCase
import com.example.netflex.usecase.SetVolumeUseCase
import com.example.netflex.usecase.UpdatePlayerStateUseCase
import com.example.netflex.viewmodel.VideoPlayerViewModel
import org.koin.compose.KoinContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

class MainActivity :
    ComponentActivity(),
    KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoin()

        setContent {
            KoinContext(getKoin()) {
                val playPauseUseCase by inject<PlayPauseUseCase>()
                val seekToUseCase by inject<SeekToUseCase>()
                val setVolumeUseCase by inject<SetVolumeUseCase>()
                val updatePlayerStateUseCase by inject<UpdatePlayerStateUseCase>()
                val videoPlayerController by inject<VideoPlayerController>()

                Box(
                    modifier =
                        Modifier
                            .windowInsetsPadding(
                                WindowInsets.systemBars.only(
                                    WindowInsetsSides.Horizontal + WindowInsetsSides.Top + WindowInsetsSides.Bottom,
                                ),
                            ),
                ) {
                    VideoPlayerScreen(
                        VideoPlayerViewModel(
                            playPauseUseCase,
                            seekToUseCase,
                            setVolumeUseCase,
                            updatePlayerStateUseCase,
                        ),
                        videoPlayerController,
                    )
                }
            }
        }
    }

    private fun loadKoin() {
        getKoin().loadModules(
            listOf(
                module {
                    single<Activity> { this@MainActivity }
                    single<ComponentActivity> { this@MainActivity }
                },
            ),
        )
    }
}
