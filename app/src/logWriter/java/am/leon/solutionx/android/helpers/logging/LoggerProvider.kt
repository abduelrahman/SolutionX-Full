package am.leon.solutionx.android.helpers.logging

import am.leon.solutionx.BuildConfig
import am.leon.solutionx.android.helpers.logging.writers.FileWriter

object LoggerProvider {
    fun provideLogger(tagKey: String = "SolutionX-Full") {
        LoggerFactory.setLogWriter(
            FileWriter(
                folderName = tagKey,
                fileName = "SolutionX-Full-logFile",
                tagKey = tagKey,
                isDebugEnabled = BuildConfig.DEBUG
            )
        )
    }
}