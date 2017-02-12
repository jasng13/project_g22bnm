package com.android.lurn.projectmanagement;


import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import com.android.lurn.projectmanagement.Models.Configurations.HttpRequest;


/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    private String PREF_GENERAL_IP_ADDRESS;
    private String PREF_GENERAL_USERNAME;
    private String PREF_GENERAL_PASSWORD;

    private void onStringResourceLoad()
    {
        PREF_GENERAL_IP_ADDRESS = getResources().getString(R.string.pref_key_ip_address);
        PREF_GENERAL_USERNAME = getResources().getString(R.string.pref_key_username);
        PREF_GENERAL_PASSWORD = getResources().getString(R.string.pref_key_password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setup the home button.
        setupActionBar();
        onStringResourceLoad();
        // Add 'general' preferences, defined in the XML file
        addPreferencesFromResource(R.xml.pref_general);
        // Bind the summaries of EditText/List/Dialog/Ringtone preferences
        // to their values. When their values change, their summaries are
        // updated to reflect the new value, per the Android Design
        // guidelines.
        bindPreferenceSummaryToValue(findPreference(PREF_GENERAL_IP_ADDRESS));
        bindPreferenceSummaryToValue(findPreference(PREF_GENERAL_USERNAME));
        bindPreferenceSummaryToValue(findPreference(PREF_GENERAL_PASSWORD));
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     */
    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        // Use the user-defined server IP Address
        if(preference.getKey().equals(PREF_GENERAL_IP_ADDRESS))
            HttpRequest.setHostname(stringValue);
        else if(preference.getKey().equals(PREF_GENERAL_USERNAME))
            HttpRequest.setUsername(stringValue);
        else if(preference.getKey().equals(PREF_GENERAL_PASSWORD))
            HttpRequest.setPassword(stringValue);
        // For all other preferences, set the summary to the value's
        // simple string representation.
        preference.setSummary(stringValue);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
