package com.example.yavor.reservations.preferences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.example.yavor.reservations.R;

/**
 * Created by mitevyav on 11.7.2017 г..
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);


        // Init and update the pref summary
        String emailPrefKey = getString(R.string.email_pref_key);
        final Preference pref = getPreferenceManager().findPreference(
                emailPrefKey);
        pref.setSummary(pref.getSharedPreferences().getString(emailPrefKey, ""));
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                pref.setSummary(newValue.toString());
                return true;
            }
        });
    }
}
