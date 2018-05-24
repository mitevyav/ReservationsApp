package com.example.yavor.reservations.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.yavor.reservations.data.model.Converters;
import com.example.yavor.reservations.data.model.Reservation;

@Database(entities = {Reservation.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ReservationDao getReservationDao();
}
