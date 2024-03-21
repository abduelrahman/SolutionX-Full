package am.leon.solutionx.android.helpers.logging

import am.leon.solutionx.android.helpers.logging.writers.DummyWriter

object LoggerFactory {
    var currentLogWriter: LogWriter = DummyWriter()
        private set

    /**
     * @param clazz the returned logger will be named after clazz
     * @return logger
     */
    fun getLogger(clazz: Class<*>): Logger {
        return Logger(clazz)
    }

    fun setLogWriter(currentLogWriter: LogWriter) {
        LoggerFactory.currentLogWriter = currentLogWriter
    }
}

/**
 * This is an extension method to replace this line
 * [private val logger: Logger = LoggerFactory.getLogger(MainActivity::class.java)]
 *
 * with the new line using the extension
 * [private val logger = getClassLogger()]
 *
 */
inline fun <reified T> T.getClassLogger(): Logger {
    if (T::class.isCompanion) {
        return LoggerFactory.getLogger(T::class.java.enclosingClass as Class<*>)
    }
    return LoggerFactory.getLogger(T::class.java)
}