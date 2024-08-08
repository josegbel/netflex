package com.example.netflex

import com.example.netflex.usecase.PlayPauseUseCase
import com.example.netflex.usecase.SeekToUseCase
import com.example.netflex.usecase.SetVolumeUseCase
import com.example.netflex.usecase.UpdatePlayerStateUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule =
    module {
        single { PlayPauseUseCase(get()) }
        single { SeekToUseCase(get()) }
        single { SetVolumeUseCase(get()) }
        single { UpdatePlayerStateUseCase(get()) }
        single(named(KOIN_VIDEO_URL)) { "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4" }
    }

const val KOIN_VIDEO_URL = "VIDEO_URL"
