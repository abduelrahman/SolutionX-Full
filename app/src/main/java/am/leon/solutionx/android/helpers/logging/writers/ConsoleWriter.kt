package am.leon.solutionx.android.helpers.logging.writers

import am.leon.solutionx.android.helpers.logging.LogWriter

class ConsoleWriter(private val tagKey: String, override val isDebugEnabled: Boolean) : LogWriter {

    override fun debug(clazz: Class<*>, message: String?) {
        if (isDebugEnabled)
            println(String.format("$tagKey D: [%s] %s", clazz.simpleName, message))
    }

    override fun info(clazz: Class<*>, message: String?) {
        if (isDebugEnabled)
            println(String.format("$tagKey I: [%s] %s", clazz.simpleName, message))
    }

    override fun warning(clazz: Class<*>, message: String?) {
        if (isDebugEnabled)
            println(String.format("$tagKey W: [%s] %s", clazz.simpleName, message))
    }

    override fun error(clazz: Class<*>, message: String?, throwable: Throwable?) {
        if (isDebugEnabled) {
            val fullMessage = if (throwable == null)
                String.format("$tagKey E: [%s] %s", clazz.simpleName, message)
            else
                String.format(
                    "$tagKey E: [%s] %s %s", clazz.simpleName, message, throwable.toString()
                )

            System.err.println(fullMessage)
        }
    }
}