package com.example.yavor.reservations.di.modules;

import com.example.yavor.reservations.ReservationApp;
import com.example.yavor.reservations.data.ReservationRepository;
import com.example.yavor.reservations.data.ReservationRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final ReservationApp reservationApp;

    public ApplicationModule(ReservationApp reservationApp) {
        this.reservationApp = reservationApp;
    }

    @Provides
    @Singleton
    ReservationRepository provideReservationRepository() {
        return new ReservationRepositoryImpl(reservationApp);
    }
}
