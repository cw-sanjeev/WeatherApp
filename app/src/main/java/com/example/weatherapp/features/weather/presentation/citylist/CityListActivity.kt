package com.example.weatherapp.features.weather.presentation.citylist

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.weather.R
import com.example.weatherapp.core.domain.base.UiError
import com.example.weatherapp.core.domain.base.UiText
import com.example.weatherapp.core.domain.local.preferences.Preferences
import com.example.weatherapp.core.presentation.base.ViewModelFactory
import com.example.weatherapp.features.weather.domain.model.City
import com.example.weatherapp.features.weather.domain.model.WeatherDetails
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CityListActivity : AppCompatActivity() {
    private lateinit var cityListAdapter: CityListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var retryButton: AppCompatButton
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var parentLayout: View

    private lateinit var permissionLaucher: ActivityResultLauncher<Array<String>>

    private val cityListViewModel: CityListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)
        parentLayout = findViewById(android.R.id.content)
        initViews()
        initAppPermissions()
        observeState()
    }

    private fun initAppPermissions() {
        permissionLaucher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            cityListViewModel.getLocation()
        }
        permissionLaucher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.city_list_view)
        progressBar = findViewById(R.id.city_list_progress_bar)
        retryButton = findViewById(R.id.city_list_try_again_button)
        swipeRefreshLayout = findViewById(R.id.swipe_container)

        swipeRefreshLayout.setOnRefreshListener {
            cityListViewModel.getCityList()
        }

        retryButton.setOnClickListener {
            cityListViewModel.getCityList()
        }

        cityListAdapter = CityListAdapter { city: City ->
            val intent = Intent(this, WeatherDetails::class.java)
            intent.putExtra(Preferences.KEY_LOCATION, city)
            startActivity(intent)
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CityListActivity)
            adapter = cityListAdapter
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                cityListViewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) progressBar.visibility =
                        View.VISIBLE else progressBar.visibility = View.GONE
                    if (uiState.useMessage != null) {
                        Snackbar.make(
                            parentLayout,
                            uiState.useMessage.asString(this@CityListActivity),
                            Snackbar.LENGTH_LONG
                        ).show().apply {
                            cityListViewModel.userMessageShown()
                        }
                    }
                    if (uiState.uiError != null) {
                        when (uiState.uiError) {
                            is UiError.NoInternet -> Snackbar.make(
                                parentLayout,
                                uiState.uiError.msg,
                                Snackbar.LENGTH_LONG
                            ).show().apply {
                                cityListViewModel.userMessageShown()
                            }

                            else -> Snackbar.make(
                                parentLayout,
                                UiText.StringResource(R.string.error_getting_location)
                                    .asString(this@CityListActivity),
                                Snackbar.LENGTH_LONG
                            ).show().apply {
                                cityListViewModel.userMessageShown()
                            }
                        }
                    }
                    if (uiState.location != null) {
                        startActivity(
                            Intent(
                                this@CityListActivity,
                                CityListActivity::class.java
                            ).putExtra(Preferences.KEY_LOCATION, uiState.location)
                        )
                    }
                    if (!uiState.cities.isNullOrEmpty()) {
                        recyclerView.visibility = View.VISIBLE
                        cityListAdapter.updateCities(uiState.cities!!)
                    }
                }
            }
        }
    }
}