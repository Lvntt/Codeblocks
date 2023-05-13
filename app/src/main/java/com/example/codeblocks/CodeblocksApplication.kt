package com.example.codeblocks

import android.app.Application
import com.example.codeblocks.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CodeblocksApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CodeblocksApplication)
            modules(
                providePresentationModule()
            )
        }
    }
}