package com.example.yavor.reservations.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.yavor.reservations.data.model.Reservation;

@Database(entities = {Reservation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReservationDao reservationDao();
}
