package com.example.yavor.reservations;

import android.app.Application;
import android.content.Context;

import com.example.yavor.reservations.di.component.ApplicationComponent;
import com.example.yavor.reservations.di.component.DaggerApplicationComponent;
import com.example.yavor.reservations.di.modules.ApplicationModule;

public class ReservationApp extends Application {

    ApplicationComponent applicationComponent;

    public static ReservationApp get(Context context) {
        return (ReservationApp) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
