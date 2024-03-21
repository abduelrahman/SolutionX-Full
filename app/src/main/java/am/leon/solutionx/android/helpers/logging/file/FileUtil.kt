package am.leon.solutionx.android.helpers.logging.file

import android.os.Environment
import android.util.Log
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

internal object FileUtil {

    /**
     * Listing out all the required file(s) in a specific directory.
     *
     * @param dirPath      Absolute path of directory containing files.
     * @param fileType Extension of file to be listed.
     * @return List of file name of desired extension.
     * @throws FileNotFoundException if given directory or file not exist
     */
    @Throws(FileNotFoundException::class)
    fun loadFilesFromDir(dirPath: String, fileType: String): ArrayList<String> {
        val path = File(dirPath)
        val list: Array<String>?
        if (path.exists()) {
            val filter = FilenameFilter { _, name ->
                name.endsWith(fileType)
            }
            list = path.list(filter)
        } else
            throw FileNotFoundException()
        return ArrayList(listOf(*list))
    }

    /**
     * Reads Json from given file.
     *
     * @param filePath Directory absolute path
     * @param fileName file name in specified directory
     * @return Json string from specified file.
     * @throws IOException If directory or file not available.
     */
    @Throws(IOException::class)
    fun readDataFromLocalStorage(filePath: String, fileName: String): String {
        val textFile = File(filePath, fileName)
        val extractedText = StringBuilder()
        val bufferedReader = BufferedReader(FileReader(textFile))
        var line: String

        while (bufferedReader.readLine().also { line = it } != null) {
            extractedText.append(line + "\n")
        }
        bufferedReader.close()
        return extractedText.toString()
    }

    private lateinit var buffer: BufferedWriter

    fun logToFile(logType: LogType, message: String) {
        try {
            buffer.apply {
                write(getFormattedMessage(logType, message))
                newLine()
                flush()
            }
        } catch (e: IOException) {
            Log.e(TAG, "IOException encountered while logging to file: ", e.cause)
            try {
                buffer.close()
            } catch (er: IOException) {
                Log.e(TAG, "IOException was encountered while closing Log writer: ", er.cause)
            }
        }
    }

    fun createLogWriter(logFile: File) {
        try {
            buffer = BufferedWriter(FileWriter(logFile, true))
        } catch (e: IOException) {
            Log.e(TAG, "IOException was encountered while creating File writer: ", e.cause)
        }
        logToFile(
            LogType.INFO, "------------------------- Start a new cycle -------------------------"
        )
    }

    fun checkPermissionsAndCreateFile(folderName: String, logFileName: String): File {
        if (isExternalStorageWritable.not()) {
            Log.e(TAG, "--> you do not have the permission to write")
            throw RuntimeException("you do not have the permission to write")
        }
        return getOrCreateNewFile(folderName, logFileName)
    }

    private fun getOrCreateNewFile(folderName: String, logFileName: String): File {
        val folderPath: String = StringBuilder().apply {
            append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
            append(File.separator)
            append(folderName)
            append(File.separator)
        }.toString()

        val folder = File(folderPath)
        val folderCreated = folder.mkdirs()
        if (folder.exists().not() && folderCreated.not())
            throw IOException("Unable to create the root folder")
        else
            Log.d(TAG, "Root folder created : ${folder.path}")

        val savedFile = File(folder, "$logFileName.txt")
        if (savedFile.exists().not()) {
            val status = savedFile.createNewFile()
            if (status.not())
                throw IOException("Unable to create file ${savedFile.name}.")
            else
                Log.d(TAG, "File ${savedFile.name} creation status: $status")
        }

        return savedFile
    }

    private fun getFormattedMessage(logType: LogType, message: String): String =
        StringBuilder().apply {
            append(logType.logType)
            append(" : ")
            append(currentDateTime)
            append(" ")
            append(message.trimIndent())
        }.toString()

    private val currentDateTime: String
        get() {
            val todayDate: Date = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US)
            return formatter.format(todayDate)
        }

    /**
     * Checks if external storage is available for read and write
     */
    private val isExternalStorageWritable: Boolean
        get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    /**
     * Checks if external storage is available to at least read
     */
    private val isExternalStorageReadable: Boolean
        get() = Environment.getExternalStorageState() in setOf(
            Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY
        )

    private const val TAG = "FileUtil"
}

enum class LogType(val logType: String) {
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR");
}