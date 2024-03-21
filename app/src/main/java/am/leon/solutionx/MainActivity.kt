package am.leon.solutionx

import am.leon.solutionx.android.helpers.logging.getClassLogger
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        logger.debug("This is a debug log....")
        logger.info("This is an info log....")
        logger.warning("This is a warning log....")
        logger.error("This is an error log....")
        logger.error("This is an error log with throwable....", Throwable("Exception....."))
    }

    companion object {
        private val logger = getClassLogger()
    }
}