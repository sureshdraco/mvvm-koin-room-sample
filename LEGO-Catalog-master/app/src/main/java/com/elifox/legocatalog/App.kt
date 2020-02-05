package com.elifox.legocatalog

import android.app.Application
import com.elifox.legocatalog.di.repositoryModule
import com.elifox.legocatalog.di.retrofitModule
import com.elifox.legocatalog.di.viewmodeModule
import com.elifox.legocatalog.util.CrashReportingTree
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())
        startKoin {
            printLogger()
            androidContext(this@App)
            modules(listOf(retrofitModule, repositoryModule, viewmodeModule))
        }
    }
}