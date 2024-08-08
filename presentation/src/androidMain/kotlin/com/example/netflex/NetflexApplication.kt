package com.example.netflex

import android.app.Application
import android.content.Context
import org.koin.dsl.module

class NetflexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            additionalModules =
                listOf(
                    module {
                        single<Context> { this@NetflexApplication }
                    },
                ),
        )
    }
}
