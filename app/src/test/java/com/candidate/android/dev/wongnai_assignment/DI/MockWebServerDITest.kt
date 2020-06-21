package com.candidate.android.dev.wongnai_assignment.DI

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val MockWebServerDITest = module {
    factory {
        MockWebServer()
    }

}