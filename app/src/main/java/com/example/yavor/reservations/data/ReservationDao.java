package com.example.yavor.reservations.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;

@Dao
public interface ReservationDao {

    @Query("SELECT * FROM reservation")
    List<Reservation> getAll();

    @Insert
    void insert(Reservation reservation);

    @Delete
    void delete(Reservation reservation);
}
