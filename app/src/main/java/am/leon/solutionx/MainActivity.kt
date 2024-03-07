package am.leon.solutionx

import am.leon.solutionx.databinding.ActivityMainBinding
import am.leon.solutionx.domain.Mockups
import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelection
import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelectionAdapter
import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelectionCallback
import am.leon.solutionx.presentation.adapters.singleSelection.SingleSelectionViewType
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SingleSelectionCallback {

    private lateinit var binding: ActivityMainBinding
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

        binding.btnFetchData.setOnClickListener {
            // You can change the list here....
            adapter.setItems(Mockups.getCountries(this))
        }
    }

    private fun viewInit() = with(binding) {
        recyclerSingleSelection.adapter = adapter
    }

    override fun onSingleItemSelected(selectedItem: SingleSelection) {
        Log.i("MainActivity", "onSingleItemSelected: $selectedItem")
        Toast.makeText(this, selectedItem.name, Toast.LENGTH_SHORT).show()
    }
}