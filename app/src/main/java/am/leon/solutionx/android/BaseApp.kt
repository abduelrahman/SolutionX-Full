package am.leon.solutionx.android

import am.leon.solutionx.android.helpers.logging.LoggerProvider
import android.app.Application

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerProvider.provideLogger()
    }
}