package com.example.yavor.reservations.preferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yavor.reservations.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
