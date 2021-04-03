package com.demo.weather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.demo.weather.R
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.mock.mockserver.MockServerManager
import com.demo.weather.util.ENABLE_SSL_PINNING
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import dagger.android.AndroidInjection
import javax.inject.Inject

class SettingsActivity : AppCompatActivity() {
    @Inject
    lateinit var mockServerManager: MockServerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        private val mockServerManager get() = (activity as SettingsActivity).mockServerManager
        private var apiCnt = 0

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.debug_setting_preferences, rootKey)
            initializeMockApiSwitches()
        }

        private fun initializeMockApiSwitches() {
            val searchApiSwitch = findPreference<SwitchPreferenceCompat>("search_api")
            searchApiSwitch?.isChecked = mockServerManager.shouldMockApi(WEATHER_API_SEARCH_URL)
            searchApiSwitch?.setOnPreferenceChangeListener { preference, newValue ->
                if (newValue == true) {
                    apiCnt++
                    mockServerManager.enableApi(WEATHER_API_SEARCH_URL, MockScenarios.SUCCESS)
                } else {
                    apiCnt--
                    mockServerManager.disableApi(WEATHER_API_SEARCH_URL)
                }
                startOrStopServer()
                true
            }
            val weatherApiSwitch = findPreference<SwitchPreferenceCompat>("weather_api")
            weatherApiSwitch?.isChecked = mockServerManager.shouldMockApi(WEATHER_API_CURRENT_WEATHER_URL)
            weatherApiSwitch?.setOnPreferenceChangeListener { preference, newValue ->
                if (newValue == true) {
                    apiCnt++
                    mockServerManager.enableApi(WEATHER_API_CURRENT_WEATHER_URL, MockScenarios.SUCCESS)
                } else {
                    apiCnt--
                    mockServerManager.disableApi(WEATHER_API_CURRENT_WEATHER_URL)
                }
                startOrStopServer()
                true
            }
        }

        private fun startOrStopServer() {
            if (apiCnt > 0) {
                if (ENABLE_SSL_PINNING) {
                    mockServerManager.startSslServer()
                } else {
                    mockServerManager.startServer()
                }
            } else {
                mockServerManager.stopServer()
            }
        }
    }
}