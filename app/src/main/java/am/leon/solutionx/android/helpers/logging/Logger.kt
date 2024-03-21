package am.leon.solutionx.android.helpers.logging

class Logger(private val clazz: Class<*>) {
    /**
     * Is the logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for the DEBUG level,
     * false otherwise.
     */
    val isDebugEnabled: Boolean
        get() = LoggerFactory.currentLogWriter.isDebugEnabled

    fun debug(message: String?) {
        LoggerFactory.currentLogWriter.debug(clazz, message)
    }

    fun info(message: String?) {
        LoggerFactory.currentLogWriter.info(clazz, message)
    }

    fun warning(message: String?) {
        LoggerFactory.currentLogWriter.warning(clazz, message)
    }

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     *
     * @param message the message accompanying the exception
     * @param throwable   the exception (throwable) to log
     */
    fun error(message: String?, throwable: Throwable? = null) {
        LoggerFactory.currentLogWriter.error(clazz, message, throwable)
    }
}