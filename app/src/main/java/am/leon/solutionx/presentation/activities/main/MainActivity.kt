package am.leon.solutionx.presentation.activities.main

import am.leon.solutionx.R
import am.leon.solutionx.android.helpers.logging.getClassLogger
import am.leon.solutionx.databinding.ActivityMainBinding
import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelection
import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelectionAdapter
import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelectionCallback
import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelectionViewType
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SingleSelectionCallback {

    private lateinit var binding: ActivityMainBinding
    private val mainVM: MainVM by viewModels<MainVM>()

    private val adapter: SingleSelectionAdapter by lazy {
        // You can change the viewType here....
        SingleSelectionAdapter(SingleSelectionViewType.SELECTION_CHECK, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewInit()
        subscribeToObservables()

        binding.btnFetchData.setOnClickListener {
            mainVM.getCountries()
        }
    }

    private fun subscribeToObservables() {
    }

    private fun viewInit() = with(binding) {
        recyclerSingleSelection.adapter = adapter
    }

    override fun onSingleItemSelected(selectedItem: SingleSelection) {
        logger.warning("onSingleItemSelected: $selectedItem")
        Toast.makeText(this, selectedItem.name, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val logger = getClassLogger()
    }
}