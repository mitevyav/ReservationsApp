package com.example.yavor.reservations.di.component;

import com.example.yavor.reservations.data.ReservationRepository;
import com.example.yavor.reservations.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    ReservationRepository getReservationRepository();

}
