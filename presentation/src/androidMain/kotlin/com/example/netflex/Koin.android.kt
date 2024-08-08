package com.example.netflex

import com.example.netflex.video.AndroidVideoPlayerController
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal actual val platformModule: Module =
    module {
        single<VideoPlayerController> { AndroidVideoPlayerController(get(), get(named(KOIN_VIDEO_URL))) }
    }
