package com.example.yavor.reservations.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.yavor.reservations.R;

/**
 * Created by mitevyav on 11.7.2017 г..
 */

public class PreferenceUtils {

    private PreferenceUtils() {

    }

    public static String getAdminEmail(Context context) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(context.getString(R.string.email_pref_key),
                "");
    }
}
