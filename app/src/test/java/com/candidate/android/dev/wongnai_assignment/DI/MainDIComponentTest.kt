package com.candidate.android.dev.wongnai_assignment.DI

fun configureTestAppComponent()
        = listOf(
    MockWebServerDITest,
    configureNetworkModuleForTest()
)