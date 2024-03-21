package am.leon.solutionx.android.helpers.logging

interface LogWriter {
    val isDebugEnabled: Boolean
    fun debug(clazz: Class<*>, message: String?)
    fun info(clazz: Class<*>, message: String?)
    fun warning(clazz: Class<*>, message: String?)
    fun error(clazz: Class<*>, message: String?, throwable: Throwable? = null)
}