package com.example.netflex.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.example.netflex.VideoPlayerController
import com.example.netflex.initKoin
import com.example.netflex.usecase.PlayPauseUseCase
import com.example.netflex.usecase.SeekToUseCase
import com.example.netflex.usecase.SetVolumeUseCase
import com.example.netflex.usecase.UpdatePlayerStateUseCase
import com.example.netflex.viewmodel.VideoPlayerViewModel
import org.koin.dsl.module
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow

@Suppress("unused", "FunctionNaming", "FunctionName")
fun MainViewController(window: UIWindow): UIViewController {
    val koinApplication = initKoin()
    val koin = koinApplication.koin

    val uiViewController =
        ComposeUIViewController {
            val playPauseUseCase by koin.inject<PlayPauseUseCase>()
            val seekToUseCase by koin.inject<SeekToUseCase>()
            val setVolumeUseCase by koin.inject<SetVolumeUseCase>()
            val updatePlayerStateUseCase by koin.inject<UpdatePlayerStateUseCase>()
            val videoPlayerController by koin.inject<VideoPlayerController>()

            Box(
                Modifier.windowInsetsPadding(
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

    koinApplication.koin.loadModules(
        listOf(
            module { single<() -> UIViewController> { { uiViewController } } },
        ),
    )

    return uiViewController
}
