package am.leon.solutionx.android.helpers.logging.writers

import am.leon.solutionx.android.helpers.logging.LogWriter
import am.leon.solutionx.android.helpers.logging.file.FileUtil
import am.leon.solutionx.android.helpers.logging.file.LogType
import android.util.Log
import java.io.File

class FileWriter(
    folderName: String, fileName: String, private val tagKey: String,
    override val isDebugEnabled: Boolean
) : LogWriter {

    override fun debug(clazz: Class<*>, message: String?) {
        if (isDebugEnabled) {
            val formattedMessage = getFormattedMessage(clazz, message)
            Log.d(tagKey, formattedMessage)
            FileUtil.logToFile(LogType.DEBUG, formattedMessage)
        }
    }

    override fun info(clazz: Class<*>, message: String?) {
        if (isDebugEnabled) {
            val formattedMessage = getFormattedMessage(clazz, message)
            Log.i(tagKey, formattedMessage)
            FileUtil.logToFile(LogType.INFO, formattedMessage)
        }
    }

    override fun warning(clazz: Class<*>, message: String?) {
        if (isDebugEnabled) {
            val formattedMessage = getFormattedMessage(clazz, message)
            Log.w(tagKey, formattedMessage)
            FileUtil.logToFile(LogType.WARNING, formattedMessage)
        }
    }

    override fun error(clazz: Class<*>, message: String?, throwable: Throwable?) {
        if (isDebugEnabled) {
            val formattedMessage = getFormattedMessage(clazz, message, throwable)
            Log.e(tagKey, formattedMessage)
            FileUtil.logToFile(LogType.ERROR, formattedMessage)
        }
    }

    private fun getFormattedMessage(
        clazz: Class<*>, message: String?, throwable: Throwable? = null
    ): String {
        return if (throwable == null)
            String.format("[%s] %s", clazz.simpleName, message)
        else
            String.format("[%s] %s %s", clazz.simpleName, message, throwable.toString())
    }

    init {
        if (isDebugEnabled) {
            val logFile: File = FileUtil.checkPermissionsAndCreateFile(folderName, fileName)
            FileUtil.createLogWriter(logFile)
        }
    }
}