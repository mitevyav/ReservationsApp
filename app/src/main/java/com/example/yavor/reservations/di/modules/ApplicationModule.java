package com.example.yavor.reservations.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.yavor.reservations.data.AppDatabase;
import com.example.yavor.reservations.data.ReservationDao;
import com.example.yavor.reservations.data.ReservationRepository;
import com.example.yavor.reservations.data.ReservationRepositoryImpl;
import com.example.yavor.reservations.di.ApplicationContext;
import com.example.yavor.reservations.di.DatabaseInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    ReservationRepository provideReservationRepository(ReservationDao reservationDao) {
        return new ReservationRepositoryImpl(reservationDao);
    }


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@ApplicationContext Context context, @DatabaseInfo String dbName) {
        return Room.databaseBuilder(context,
                AppDatabase.class, dbName)
                .build();
    }


    @Provides
    @Singleton
    ReservationDao provideReservationDao(AppDatabase database) {
        return database.getReservationDao();
    }


    @ApplicationContext
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "reservations.db";
    }


}
