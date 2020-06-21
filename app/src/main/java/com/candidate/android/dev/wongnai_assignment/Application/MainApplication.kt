package com.candidate.android.dev.wongnai_assignment.Application

import android.app.Application
import android.util.Log
import com.candidate.android.dev.wongnai_assignment.BuildConfig
import com.candidate.android.dev.wongnai_assignment.DI.assignmentModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(assignmentModule)
        }
        setUpTimber()
    }

    private fun setUpTimber(){
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}