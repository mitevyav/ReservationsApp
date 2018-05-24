package com.example.yavor.reservations;

import android.app.Application;

import com.example.yavor.reservations.di.component.DaggerApplicationComponent;
import com.example.yavor.reservations.di.modules.ApplicationModule;

public class ReservationApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
