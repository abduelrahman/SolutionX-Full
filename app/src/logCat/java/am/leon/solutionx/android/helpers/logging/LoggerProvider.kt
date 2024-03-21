package am.leon.solutionx.android.helpers.logging

import am.leon.solutionx.BuildConfig
import am.leon.solutionx.android.helpers.logging.writers.LogcatWriter

object LoggerProvider {
    fun provideLogger(tagKey: String = "SolutionX-Full") {
        LoggerFactory.setLogWriter(LogcatWriter(tagKey, BuildConfig.DEBUG))
    }
}