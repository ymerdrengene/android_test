package dk.harj_it.leoinnovationlabtest

import android.app.Application
import timber.log.Timber

class LeoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}