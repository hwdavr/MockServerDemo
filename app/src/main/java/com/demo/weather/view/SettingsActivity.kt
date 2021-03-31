package com.demo.weather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.demo.weather.BuildConfig
import com.demo.weather.R
import com.demo.weather.mockserver.MockScenarios
import com.demo.weather.mockserver.MockServerManager
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

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            if (BuildConfig.DEBUG) {
                setPreferencesFromResource(R.xml.debug_setting_preferences, rootKey)
                initializeMockApiSwitches()
            } else {
                setPreferencesFromResource(R.xml.setting_preferences, rootKey)
            }
        }

        private fun initializeMockApiSwitches() {
            val searchApiSwitch = findPreference<SwitchPreferenceCompat>("search_api")
            searchApiSwitch?.isChecked = false
            searchApiSwitch?.setOnPreferenceChangeListener { preference, newValue ->
                if (newValue == true) {
                    mockServerManager.enableApi(WEATHER_API_SEARCH_URL, MockScenarios.SUCCESS)
                } else {
                    mockServerManager.disableApi(WEATHER_API_SEARCH_URL)
                }
                enableMockServer()
                true
            }
            val weatherApiSwitch = findPreference<SwitchPreferenceCompat>("weather_api")
            weatherApiSwitch?.isChecked = false
            weatherApiSwitch?.setOnPreferenceChangeListener { preference, newValue ->
                if (newValue == true) {
                    mockServerManager.enableApi(WEATHER_API_CURRENT_WEATHER_URL, MockScenarios.SUCCESS)
                } else {
                    mockServerManager.disableApi(WEATHER_API_CURRENT_WEATHER_URL)
                }
                enableMockServer()
                true
            }
        }

        private fun enableMockServer() {
            mockServerManager.enableMockServer()
        }
    }
}