package com.example.netflex

import org.koin.core.context.startKoin
import org.koin.core.module.Module

internal fun initKoin(additionalModules: List<Module> = emptyList()) =
    startKoin {
        modules(listOf(dataModule, domainModule, platformModule) + additionalModules)
        createEagerInstances()
    }

internal expect val platformModule: Module
