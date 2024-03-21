package am.leon.solutionx.android.helpers.logging

import am.leon.solutionx.android.helpers.logging.writers.DummyWriter

object LoggerProvider {
    fun provideLogger() {
        LoggerFactory.setLogWriter(DummyWriter())
    }
}